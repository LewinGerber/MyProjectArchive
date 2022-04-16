package ch.zli.onenote.service;

import ch.zli.onenote.entities.User;
import ch.zli.onenote.entities.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    //@Value("${jwt.signing.key}")
    public String SIGNING_KEY = "@#27_CJ#EaVDVrMNEYYZfb&h7Z1WFkZ&&Wp8N2QCk8C@t14g9Qy$Ps%4*@#27_CJ#EaVDVrMNEYYZfb&h7Z1WFkZ&&Wp8N2QCk8C@t14g9Qy$Ps%4*";

    //@Value("${jwt.header.string}")
    public String HEADER = "Authorization";

    //@Value("${jwt.token.prefix}")
    public String PREFIX = "Bearer";

    public int getIdWithToken(String token) {
        token = token.replace(PREFIX + " ", "");
        String username = Jwts.parser()
                .setSigningKey(SIGNING_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody().getSubject();

        if (username != null) {
            User user = userRepository.findByEmail(username);
            return user.getId();
        }
        return -1;
    }
}
