package ch.bbw.webshop;

import ch.bbw.webshop.entity.DBManager;
import ch.bbw.webshop.entity.User;
import ch.bbw.webshop.security.JwtAuthenticationUserModel;
import ch.bbw.webshop.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @version 12.01.2021
 * webshop - AuthenticationControllerREST
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class AuthenticationControllerREST {

    @Autowired
    private DBManager DBMANAGER;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    // create a new user and save to DB
    // on POST, always send JSON including the User - as AuthenticationUserModel
    @PostMapping(value = "/registerUser", produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody JwtAuthenticationUserModel inputUser) {
        // return 409 ERROR if user already exists in DB (with same EMAIL)
        if (DBMANAGER.findUser(inputUser.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        // if user doesn't exist => add to DB, encrypt password and return as http response OK 200
        User newUser = new User(inputUser.getEmail(), bcryptEncoder.encode(inputUser.getPassword()));
        System.out.println(newUser.getEmail());
        DBMANAGER.saveUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping(value = "/registerUser")
    public void addUser() {
        User newUser = new User("root", bcryptEncoder.encode("123"));
        DBMANAGER.saveUser(newUser);
    }

    // authenticate user with given credentials in DB
    // on POST, always send JSON including the User - as AuthenticationUserModel
    @PostMapping(value = "/authenticateUser", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationUserModel inputUser) {
        // when authentication fails, return with status code 401 UNAUTHORIZED
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getEmail(), inputUser.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // generate token with corresponding email in DB (prevents errors) and returns it with status OK 200
        return new ResponseEntity<>(jwtTokenUtil.generateToken(DBMANAGER.findUser(inputUser.getEmail()).getEmail()), HttpStatus.OK);
    }

    // return boolean if user is authenticated and the token is still valid
    @GetMapping(value = "/isAuthenticated", produces = "application/json")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(value = "Authorization", defaultValue = "") String token) {
        if(!jwtTokenUtil.checkTokenHeader(token)) return new ResponseEntity<>(false, HttpStatus.OK);
        String validationToken = token.substring(7);
        if (jwtTokenUtil.validateToken(validationToken, DBMANAGER.findUser(jwtTokenUtil.getEmailFromToken(validationToken)))) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.LOCKED);
    }
}
