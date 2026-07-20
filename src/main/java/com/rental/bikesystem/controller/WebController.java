package com.rental.bikesystem.controller;

import com.rental.bikesystem.entity.Bike;
import com.rental.bikesystem.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.rental.bikesystem.entity.Rental;
import com.rental.bikesystem.repository.BikeRepository;
import com.rental.bikesystem.service.RentalService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("/")
    public String home(Model model) {
        List<Bike> bikes = bikeService.getAllBikes();
        model.addAttribute("bikes", bikes);
        return "index";
    }
    
    @Autowired
    private RentalService rentalService;

    @Autowired
    private BikeRepository bikeRepository;

    // Show booking form for a specific bike
    @GetMapping("/rent/{bikeId}")
    public String rentForm(@PathVariable Long bikeId, Model model) {
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() -> new RuntimeException("Bike not found"));
        model.addAttribute("bike", bike);
        return "rent-form";
    }

    // Process the booking
    @PostMapping("/rent")
    public String processRent(@RequestParam Long bikeId,
                               @RequestParam String renterName,
                               @RequestParam String renterPhone,
                               Model model) {
        Rental rental = rentalService.rentBike(bikeId, renterName, renterPhone);
        model.addAttribute("rental", rental);
        return "rent-confirmation";
    }
    
 // Show return lookup form
    @GetMapping("/return")
    public String returnLookupForm() {
        return "return-lookup";
    }

    // Process lookup - show active rental for that phone number
    @PostMapping("/return/search")
    public String searchActiveRental(@RequestParam String phone, Model model) {
        List<Rental> rentals = rentalService.getRentalHistory(phone);
        Rental activeRental = rentals.stream()
                .filter(r -> r.getStatus() == com.rental.bikesystem.entity.RentalStatus.ACTIVE)
                .findFirst()
                .orElse(null);

        if (activeRental == null) {
            model.addAttribute("notFound", true);
            return "return-lookup";
        }

        model.addAttribute("rental", activeRental);
        return "return-confirm";
    }

    // Complete the return
    @PostMapping("/return/complete")
    public String completeReturn(@RequestParam Long bikeId, Model model) {
        Rental rental = rentalService.returnBike(bikeId);
        model.addAttribute("rental", rental);
        return "return-success";
    }
}