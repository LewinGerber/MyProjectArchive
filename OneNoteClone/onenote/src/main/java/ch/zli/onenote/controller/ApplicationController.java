package ch.zli.onenote.controller;

import ch.zli.onenote.entities.LoginData;
import ch.zli.onenote.entities.User;
import ch.zli.onenote.service.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ApplicationController {

    /*
        This controller is part of the used MVC-pattern.
        It only distributes data from the view to the service.
    */

    @Autowired
    ApplicationServiceImpl applicationService;

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.status(HttpStatus.OK).body("HELLO THERE");
    }

    // called on login to verify user credentials
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginData loginData) {
        User user = applicationService.validateCredentials(loginData.getEmail(), loginData.getPassword());
        if(user != null) {
            String JWTToken = applicationService.generateJWTToken(user);
            return ResponseEntity.status(HttpStatus.OK).body(JWTToken);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
