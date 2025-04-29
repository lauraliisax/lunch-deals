package com.app.lunchDeals.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class LunchDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;
    private String restaurantName;
    private String dish;
    private Double price;
    private LocalDate date;
    private String location;
    private String website;
    private String address;
    private String phoneNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status; // "ACTIVE", "SCHEDULED", "EXPIRED"

    public void setStatus(String active) {
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setId(Long dealId) {
        this.id = dealId;
    }
}

