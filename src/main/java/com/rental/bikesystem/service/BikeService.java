package com.rental.bikesystem.service;

import com.rental.bikesystem.entity.Bike;
import com.rental.bikesystem.entity.BikeCategory;
import com.rental.bikesystem.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    // Get all bikes
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    // Get only available bikes
    public List<Bike> getAvailableBikes() {
        return bikeRepository.findByAvailableTrue();
    }

    // Get bikes by category
    public List<Bike> getBikesByCategory(BikeCategory category) {
        return bikeRepository.findByCategory(category);
    }

    // Get a single bike by id
    public Optional<Bike> getBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    // Add a new bike
    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    // Update an existing bike
    public Bike updateBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    // Delete a bike
    public void deleteBike(Long id) {
        bikeRepository.deleteById(id);
    }

    // Mark a bike unavailable (e.g. when rented)
    public void markUnavailable(Long id) {
        bikeRepository.findById(id).ifPresent(bike -> {
            bike.setAvailable(false);
            bikeRepository.save(bike);
        });
    }

    // Mark a bike available (e.g. when returned)
    public void markAvailable(Long id) {
        bikeRepository.findById(id).ifPresent(bike -> {
            bike.setAvailable(true);
            bikeRepository.save(bike);
        });
    }
}