package com.app.lunchDeals.repository;

import com.app.lunchDeals.entity.LunchDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LunchDealRepository extends JpaRepository<LunchDeal, Long> {
    List<LunchDeal> findByDateAndLocation(LocalDate date, String location);
    List<LunchDeal> findByRestaurantNameContainingIgnoreCase(String restaurantName);

    List<LunchDeal> findByDateAndStatus(LocalDate today, String scheduled);
}
