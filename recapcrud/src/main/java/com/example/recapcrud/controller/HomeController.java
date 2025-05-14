package com.example.recapcrud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Value("${spring.datasource.password}")
    private String password;


    @RequestMapping("/")
    public String index() {
        String viewName = getName();
//        return "index.html";
        System.out.println("Password: " + password);
        return viewName;
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    private String getName() {
        return "index.html";
    }
}
