package com.rental.bikesystem.controller;

import com.rental.bikesystem.entity.Rental;
import com.rental.bikesystem.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    // Rent a bike
    @PostMapping("/rent")
    public Rental rentBike(@RequestParam Long bikeId,
                            @RequestParam String renterName,
                            @RequestParam String renterPhone) {
        return rentalService.rentBike(bikeId, renterName, renterPhone);
    }

    // Return a bike
    @PostMapping("/return")
    public Rental returnBike(@RequestParam Long bikeId) {
        return rentalService.returnBike(bikeId);
    }

    // Get all active rentals
    @GetMapping("/active")
    public List<Rental> getActiveRentals() {
        return rentalService.getActiveRentals();
    }

    // Get rental history by phone
    @GetMapping("/history")
    public List<Rental> getRentalHistory(@RequestParam String phone) {
        return rentalService.getRentalHistory(phone);
    }

    // Admin: get all rentals
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }
}