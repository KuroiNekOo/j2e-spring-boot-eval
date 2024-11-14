package com.example.gomesdinis.maxime.controller;

import com.example.gomesdinis.maxime.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private CarService carService;

    /** Home page. */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome");
        return "home";
    }

    /** Login page. */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", "Welcome to login");
        return "login";
    }

    /** Keyce page. */
    @GetMapping("/keyce")
    public String keyce(Model model) {
        model.addAttribute("message", "Welcome to Keyce");
        model.addAttribute("cars", carService.getAllCars());
        return "keyce";
    }
}