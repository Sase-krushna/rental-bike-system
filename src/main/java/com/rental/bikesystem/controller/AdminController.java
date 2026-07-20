package com.rental.bikesystem.controller;

import com.rental.bikesystem.entity.Bike;
import com.rental.bikesystem.entity.BikeCategory;
import com.rental.bikesystem.service.BikeService;
import com.rental.bikesystem.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BikeService bikeService;

    @Autowired
    private RentalService rentalService;

    // Admin dashboard - list all bikes
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("bikes", bikeService.getAllBikes());
        return "admin-dashboard";
    }

    // Show add bike form
    @GetMapping("/bikes/new")
    public String newBikeForm(Model model) {
        model.addAttribute("bike", new Bike());
        model.addAttribute("categories", BikeCategory.values());
        return "admin-bike-form";
    }

    // Show edit bike form
    @GetMapping("/bikes/{id}/edit")
    public String editBikeForm(@PathVariable Long id, Model model) {
        Bike bike = bikeService.getBikeById(id)
                .orElseThrow(() -> new RuntimeException("Bike not found"));
        model.addAttribute("bike", bike);
        model.addAttribute("categories", BikeCategory.values());
        return "admin-bike-form";
    }

    // Save (add or update) a bike
    @PostMapping("/bikes/save")
    public String saveBike(@ModelAttribute Bike bike) {
        if (bike.getAvailable() == null) {
            bike.setAvailable(true);
        }
        bikeService.addBike(bike);
        return "redirect:/admin";
    }

    // Delete a bike
    @PostMapping("/bikes/{id}/delete")
    public String deleteBike(@PathVariable Long id) {
        bikeService.deleteBike(id);
        return "redirect:/admin";
    }

    // View all rentals
    @GetMapping("/rentals")
    public String allRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "admin-rentals";
    }
}