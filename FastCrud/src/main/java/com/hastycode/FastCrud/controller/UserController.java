package com.hastycode.FastCrud.controller;

import com.hastycode.FastCrud.model.User;
import com.hastycode.FastCrud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome home!";
    }

    @GetMapping("/users")
    public ResponseEntity <List<User>> allUsers() {
        return new ResponseEntity<>(service.allUsers(), HttpStatus.OK) ;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = service.getUserById(id);

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user != null) {
            return new ResponseEntity<>(service.addUser(user), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User prevUser = service.getUserById(id);

        if (prevUser != null) {
            return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        User user = service.getUserById(id);

        if(user != null) {
            service.deleteUser(id);
            return new ResponseEntity<>("User Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
        }
    }


}
