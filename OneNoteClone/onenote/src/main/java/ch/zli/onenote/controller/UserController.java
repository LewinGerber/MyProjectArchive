package ch.zli.onenote.controller;

import ch.zli.onenote.entities.Content;
import ch.zli.onenote.entities.LoginData;
import ch.zli.onenote.entities.User;
import ch.zli.onenote.service.ContentServiceImpl;
import ch.zli.onenote.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    /*
        This controller is part of the used MVC-pattern.
        It only distributes data from the view to the service.
    */

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ContentServiceImpl contentService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null)
            return ResponseEntity.status(HttpStatus.OK).body(users);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody LoginData loginData) {
        User newUser = new User();
        newUser.setEmail(loginData.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(loginData.getPassword()));
        newUser = userService.registerUser(newUser);
        // create content for user
        Content content = new Content();
        content.setUser(newUser);
        contentService.addContent(content);
        if (newUser != null)
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping
    public ResponseEntity editUser(@RequestBody LoginData loginData) {
        User editedUser = userService.getUser(loginData.getId());
        editedUser.setEmail(loginData.getEmail());
        editedUser.setPassword(bCryptPasswordEncoder.encode(loginData.getPassword()));
        editedUser = userService.editUser(editedUser);
        if (editedUser != null)
            return ResponseEntity.status(HttpStatus.OK).body(editedUser);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id) {
        boolean deleteStatus = userService.removeUser(id);
        if (deleteStatus)
            return ResponseEntity.status(HttpStatus.OK).body(deleteStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
