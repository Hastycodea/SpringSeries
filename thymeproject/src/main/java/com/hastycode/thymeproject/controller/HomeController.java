package com.hastycode.thymeproject.controller;

import com.hastycode.thymeproject.model.Mate;
import com.hastycode.thymeproject.service.MateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    private MateService service;

    public HomeController(MateService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mateForm", new Mate());
        return "home";
    }

    @PostMapping("/")
    public String registerMate(@ModelAttribute Mate mate, Model model) {
        service.registerMate(mate);
        System.out.println(mate.toString());
        model.addAttribute("mateForm", new Mate());
        return "home";
    }


}
