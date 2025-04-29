package com.app.lunchDeals.controller;

import com.app.lunchDeals.entity.Restaurant;
import com.app.lunchDeals.repository.RestaurantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;

    public RestaurantController(RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody Restaurant restaurant) {
        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
        restaurantRepository.save(restaurant);
        return "Restaurant registered successfully";
    }
}