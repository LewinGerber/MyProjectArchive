package ch.zli.onenote.service;

import ch.zli.onenote.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private UserServiceImpl userService;

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    @Value("${jwt.token.prefix}")
    public String PREFIX;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());

    @Override
    public User validateCredentials(String email, String password) {
        // get the user with email
        User user = userService.getUser(email);
        if (user != null) {
            // compare the encoded and raw password
            if (bCryptPasswordEncoder.matches(password, user.getPassword()))
                return user;
        }
        return null;
    }

    @Override
    public String generateJWTToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000000))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY.getBytes())
                .compact();

        return PREFIX + " " + token;
    }
}
