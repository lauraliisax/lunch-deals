package com.app.lunchDeals.controller;

import com.app.lunchDeals.dto.RestaurantRegistrationRequest;
import com.app.lunchDeals.entity.RegistrationRequest;
import com.app.lunchDeals.repository.RegistrationReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RegistrationReqRepository registrationReqRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        RegistrationRequest regRequest = new RegistrationRequest();
        regRequest.setUsername(request.getUsername());
        //hash password even before approval for security
        regRequest.setPassword(passwordEncoder.encode(request.getPassword()));
        regRequest.setRestaurantName(request.getRestaurantName());
        regRequest.setCity(request.getCity());
        regRequest.setStatus("PENDING");
        registrationReqRepository.save(regRequest);
        return ResponseEntity.ok("Registration request submitted for approval");
    }
}