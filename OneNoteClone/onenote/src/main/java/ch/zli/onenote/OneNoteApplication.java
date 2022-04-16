package ch.zli.onenote;

import ch.zli.onenote.security.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@SpringBootApplication
public class OneNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneNoteApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		//.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// .addFilterAfter(new JWTFilter(), BasicAuthenticationFilter.class)
			http.csrf().disable()
					.addFilterAfter(new JWTFilter(), BasicAuthenticationFilter.class)
					.authorizeRequests()
					.anyRequest().permitAll();
		}
	}

}
