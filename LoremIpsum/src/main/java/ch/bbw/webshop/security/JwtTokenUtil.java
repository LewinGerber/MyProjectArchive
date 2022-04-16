package ch.bbw.webshop.security;

import ch.bbw.webshop.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @version 12.01.2021
 * webshop - JwtTokenUtil
 */

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // secret for encrypting the token
    @Value("${jwt.secret}")
    private String secret;

    // retrieve username from jwt token
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // get one specific part of token claim (email, date, etc...)
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        } catch (Exception e) {
            return null;
        }
    }

    // for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) throws Exception {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new Exception("Token isn't valid", e);
        }
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // validate token with token and user object
    public Boolean validateToken(String token, User inputUser) {
        try {
            final String fromToken = getEmailFromToken(token);
            return (inputUser.getEmail().equals(fromToken) && !isTokenExpired(token));
        } catch (NullPointerException e) {
            return false;
        }
    }

    // another form of token validation (simpler, no user comparison with DB)
    public Boolean checkTokenHeader(String token) {
        return !token.equals("") && token.length() >= 8 && getEmailFromToken(token.substring(7)) != null;
    }

    // generate token for user
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, email);
    }

    // generate full token
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return "Bearer " + Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
