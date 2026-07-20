package com.rental.bikesystem.service;

import com.rental.bikesystem.entity.Bike;
import com.rental.bikesystem.entity.Rental;
import com.rental.bikesystem.entity.RentalStatus;
import com.rental.bikesystem.repository.BikeRepository;
import com.rental.bikesystem.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BikeRepository bikeRepository;

    // Start a new rental
    public Rental rentBike(Long bikeId, String renterName, String renterPhone) {
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(() -> new RuntimeException("Bike not found"));

        if (!bike.getAvailable()) {
            throw new RuntimeException("Bike is not available");
        }

        Rental rental = new Rental(bike, renterName, renterPhone, LocalDateTime.now(), RentalStatus.ACTIVE);
        rentalRepository.save(rental);

        bike.setAvailable(false);
        bikeRepository.save(bike);

        return rental;
    }

    // Return a bike and calculate cost
    public Rental returnBike(Long bikeId) {
        Rental rental = rentalRepository.findByBikeIdAndStatus(bikeId, RentalStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("No active rental found for this bike"));

        LocalDateTime endTime = LocalDateTime.now();
        rental.setEndTime(endTime);
        rental.setStatus(RentalStatus.COMPLETED);

        long minutesRented = Duration.between(rental.getStartTime(), endTime).toMinutes();
        double hoursRented = Math.max(1, Math.ceil(minutesRented / 60.0)); // minimum 1 hour billed
        double cost = hoursRented * rental.getBike().getHourlyRate();
        rental.setTotalCost(cost);

        rentalRepository.save(rental);

        Bike bike = rental.getBike();
        bike.setAvailable(true);
        bikeRepository.save(bike);

        return rental;
    }

    // Get all active rentals
    public List<Rental> getActiveRentals() {
        return rentalRepository.findByStatus(RentalStatus.ACTIVE);
    }

    // Get rental history by phone number
    public List<Rental> getRentalHistory(String phone) {
        return rentalRepository.findByRenterPhone(phone);
    }

    // Get all rentals (admin use)
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }
}