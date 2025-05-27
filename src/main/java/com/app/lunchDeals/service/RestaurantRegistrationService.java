
package com.app.lunchDeals.service;

import com.app.lunchDeals.entity.User;
import com.app.lunchDeals.entity.Restaurant;
import com.app.lunchDeals.repository.UserRepository;
import com.app.lunchDeals.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RestaurantRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public User registerRestaurant(String username, String password, String restaurantName, String city) {
        // In production, hash the password!
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Set.of("RESTAURANT"));

        user = userRepository.save(user);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantName);
        restaurant.setCity(city);
        restaurant.setUser(user);

        restaurantRepository.save(restaurant);

        user.setRestaurant(restaurant);
        return userRepository.save(user);
    }
}
