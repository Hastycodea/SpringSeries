package com.hastycode.Login.controller;

import com.hastycode.Login.model.Users;
import com.hastycode.Login.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UsersController {

    private UsersService service;

    @GetMapping("/")
    public String home() {
        return "Welcome home!";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/users/register")
    public Users registerUser(@RequestBody Users user) {
        return service.registerUser(user);
    }
}
