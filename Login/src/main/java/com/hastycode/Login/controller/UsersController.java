package com.hastycode.Login.controller;

import com.hastycode.Login.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UsersController {

    private UsersService service;

    @GetMapping("/")
    public String home() {
        return "Welcome home!";
    }
}
