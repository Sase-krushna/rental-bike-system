package com.rental.bikesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @Autowired
    private com.rental.bikesystem.service.BikeService bikeService;

    @GetMapping("/pricing")
    public String pricing(org.springframework.ui.Model model) {
        model.addAttribute("bikes", bikeService.getAllBikes());
        return "pricing";
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}