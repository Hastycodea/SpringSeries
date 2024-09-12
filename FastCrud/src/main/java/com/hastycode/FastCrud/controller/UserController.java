package com.hastycode.FastCrud.controller;

import com.hastycode.FastCrud.model.User;
import com.hastycode.FastCrud.service.UserService;
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
    public List<User> allUsers() {
        return service.allUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @PutMapping("/users")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @PostMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User prevUser = service.getUserById(id);

        return service.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }


}
