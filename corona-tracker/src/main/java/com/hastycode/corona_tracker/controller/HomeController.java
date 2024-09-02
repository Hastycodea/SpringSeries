package com.hastycode.corona_tracker.controller;

import com.hastycode.corona_tracker.model.LocationStats;
import com.hastycode.corona_tracker.service.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusService coronaVirusService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronaVirusService.getCases());
        return "home";
    }
}
