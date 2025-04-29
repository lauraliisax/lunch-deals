package com.app.lunchDeals.service;

import com.app.lunchDeals.entity.Restaurant;
import com.app.lunchDeals.repository.RestaurantRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantDetailsService implements UserDetailsService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDetailsService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Restaurant restaurant = restaurantRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Restaurant not found"));

        return User.builder()
                .username(restaurant.getUsername())
                .password(restaurant.getPassword())
                .roles("RESTAURANT")
                .build();
    }
}

// ask if potential vulnerability in the code and how to fix it
