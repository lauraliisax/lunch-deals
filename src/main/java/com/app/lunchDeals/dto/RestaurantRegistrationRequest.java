
package com.app.lunchDeals.dto;

public class RestaurantRegistrationRequest {
    private String username;
    private String password;
    private String restaurantName;
    private String city;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
