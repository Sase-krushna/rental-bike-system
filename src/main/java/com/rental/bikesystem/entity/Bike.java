package com.rental.bikesystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BikeCategory category;

    @Column(nullable = false)
    private Double hourlyRate;

    @Column(nullable = false)
    private Boolean available = true;

    private String imageUrl;

    // Constructors
    public Bike() {
    }

    public Bike(String name, String model, BikeCategory category, Double hourlyRate, Boolean available, String imageUrl) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.hourlyRate = hourlyRate;
        this.available = available;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BikeCategory getCategory() {
        return category;
    }

    public void setCategory(BikeCategory category) {
        this.category = category;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}