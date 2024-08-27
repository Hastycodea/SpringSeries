package com.hastycode.SpringSecurity.controller;

import com.hastycode.SpringSecurity.model.Users;
import com.hastycode.SpringSecurity.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UsersService service;

    public UserController(UsersService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String userHome() {
        return "Welcome user!";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }
}
