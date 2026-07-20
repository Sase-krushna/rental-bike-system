package com.rental.bikesystem.repository;

import com.rental.bikesystem.entity.Bike;
import com.rental.bikesystem.entity.BikeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findByCategory(BikeCategory category);

    List<Bike> findByAvailableTrue();
}