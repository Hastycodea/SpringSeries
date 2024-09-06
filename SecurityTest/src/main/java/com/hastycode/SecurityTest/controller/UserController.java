package com.hastycode.SecurityTest.controller;


import com.hastycode.SecurityTest.model.Users;
import com.hastycode.SecurityTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/test")
    public String home() {
        return "Welcome home";
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return service.getUsers();
    }

    @PostMapping("/register")
    public Users addUser(@RequestBody Users user) {
        return service.addUser(user);
    }
}
