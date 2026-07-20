package com.rental.bikesystem.controller;

import com.rental.bikesystem.entity.Bike;
import com.rental.bikesystem.entity.BikeCategory;
import com.rental.bikesystem.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    // Get all bikes
    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    // Get only available bikes
    @GetMapping("/available")
    public List<Bike> getAvailableBikes() {
        return bikeService.getAvailableBikes();
    }

    // Get bikes by category
    @GetMapping("/category/{category}")
    public List<Bike> getBikesByCategory(@PathVariable BikeCategory category) {
        return bikeService.getBikesByCategory(category);
    }

    // Get a single bike by id
    @GetMapping("/{id}")
    public Optional<Bike> getBikeById(@PathVariable Long id) {
        return bikeService.getBikeById(id);
    }

    // Add a new bike
    @PostMapping
    public Bike addBike(@RequestBody Bike bike) {
        return bikeService.addBike(bike);
    }

    // Update a bike
    @PutMapping("/{id}")
    public Bike updateBike(@PathVariable Long id, @RequestBody Bike bike) {
        bike.setId(id);
        return bikeService.updateBike(bike);
    }

    // Delete a bike
    @DeleteMapping("/{id}")
    public void deleteBike(@PathVariable Long id) {
        bikeService.deleteBike(id);
    }
}