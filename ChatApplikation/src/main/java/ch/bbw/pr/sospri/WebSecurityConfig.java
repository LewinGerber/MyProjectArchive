package ch.bbw.pr.sospri;

import ch.bbw.pr.sospri.member.Member;
import ch.bbw.pr.sospri.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lewin Gerber
 * @version 24.03.2021
 * 50_02_SpringBootChatAppSoSpriV01
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("user");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("admin");
    }
    */

    @Autowired
    MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.memberService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    // checks if a month has passed between the two dates
    public boolean monthPassed(Date start, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int startMonth = c1.get(Calendar.MONTH);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int endMonth = c2.get(Calendar.MONTH);

        if(endMonth > startMonth) {
            double percentageDoneC1 = ((double)c1.get(Calendar.DAY_OF_MONTH) / (double)c1.getActualMaximum(Calendar.DAY_OF_MONTH));
            double percentageDoneC2 = ((double)c2.get(Calendar.DAY_OF_MONTH) / (double)c2.getActualMaximum(Calendar.DAY_OF_MONTH));
            if(percentageDoneC1 <= percentageDoneC2) return true;
        }
        return false;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/*", "/fragments/*", "/img/*").permitAll()
                .antMatchers("/get-register", "/", "/error", "/reset-password-challenge").permitAll()
                .antMatchers("/get-members").hasAuthority("admin")
                .antMatchers("/get-channel").hasAnyAuthority("admin", "supervisor", "member")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication auth) throws IOException {
                            Member member = memberService.getByUserName(auth.getName());
                            boolean resetPassword = monthPassed(
                                    new Date(member.getDate().getTime()),
                                    new Date(System.currentTimeMillis()));

                            HttpSession session = request.getSession();
                            SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                            if(savedRequest != null) {
                                String url = "";
                                String[] urlParts = savedRequest.getRedirectUrl().split("//")[1].split("/");
                                for(int i = 0; i < urlParts.length; i++)
                                    if(i != 0) url += "/" + urlParts[i];

                                if(resetPassword) {
                                    response.sendRedirect("/reset-password?url=" + url);
                                } else {
                                    response.sendRedirect(savedRequest.getRedirectUrl());
                                }
                            } else {
                                response.sendRedirect(request.getContextPath());
                            }
                        }
                    })
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .permitAll()
                .and().exceptionHandling().accessDeniedPage("/403.html")
                .and().httpBasic();

        http.csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();

        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }
}
