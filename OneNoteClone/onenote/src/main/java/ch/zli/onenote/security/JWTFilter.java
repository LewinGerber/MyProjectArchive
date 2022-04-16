package ch.zli.onenote.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JWTFilter extends OncePerRequestFilter {

    //@Value("${jwt.signing.key}")
    public String SIGNING_KEY = "@#27_CJ#EaVDVrMNEYYZfb&h7Z1WFkZ&&Wp8N2QCk8C@t14g9Qy$Ps%4*@#27_CJ#EaVDVrMNEYYZfb&h7Z1WFkZ&&Wp8N2QCk8C@t14g9Qy$Ps%4*";

    //@Value("${jwt.header.string}")
    public String HEADER = "Authorization";

    //@Value("${jwt.token.prefix}")
    public String PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String header = "";
        try {
            header = req.getHeader(HEADER);
        } catch (NullPointerException e) { }

        if (header != null && header != "") {
            if (authenticateToken(header)) {
                forgeAuthentication();
            } else {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(req, res);
    }

    public boolean authenticateToken(String token) {
        token = token.replace(PREFIX + " ", "");
        String username = Jwts.parser()
                .setSigningKey(SIGNING_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody().getSubject();

        if (username.equals("username"))
            return true;
        return false;
    }

    public void forgeAuthentication() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "Granted", null, Arrays.asList("Granted").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}