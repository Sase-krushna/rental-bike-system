package com.rental.bikesystem.repository;

import com.rental.bikesystem.entity.Rental;
import com.rental.bikesystem.entity.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByStatus(RentalStatus status);

    Optional<Rental> findByBikeIdAndStatus(Long bikeId, RentalStatus status);

    List<Rental> findByRenterPhone(String renterPhone);
}