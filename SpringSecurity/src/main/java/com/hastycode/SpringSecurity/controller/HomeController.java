package com.hastycode.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        return  "Welcome home! " + request.getSession().getId() ;
    }
}
