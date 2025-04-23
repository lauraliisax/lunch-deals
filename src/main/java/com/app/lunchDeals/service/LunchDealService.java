package com.app.lunchDeals.service;

import com.app.lunchDeals.entity.LunchDeal;
import com.app.lunchDeals.repository.LunchDealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LunchDealService {
    private final LunchDealRepository lunchDealRepository;

    public LunchDealService(LunchDealRepository lunchDealRepository) {
        this.lunchDealRepository = lunchDealRepository;
    }

    // Creates a new deal
    public LunchDeal createDeal(Long restaurantId, LunchDeal deal) {
        deal.setRestaurantId(restaurantId);
        return lunchDealRepository.save(deal);
    }

    // Gets all deals for a specific restaurant
    public List<LunchDeal> getDealsByRestaurant(Long restaurantId) {
        return lunchDealRepository.findAll().stream()
                .filter(deal -> deal.getRestaurantId().equals(restaurantId))
                .toList();
    }

    // Updates an existing deal
    public LunchDeal updateDeal(Long restaurantId, Long dealId, LunchDeal updatedDeal) {
        Optional<LunchDeal> existingDeal = lunchDealRepository.findById(dealId);
        if (existingDeal.isPresent() && existingDeal.get().getRestaurantId().equals(restaurantId)) {
            updatedDeal.setId(dealId);
            updatedDeal.setRestaurantId(restaurantId);
            return lunchDealRepository.save(updatedDeal);
        } else {
            throw new IllegalArgumentException("Deal not found or unauthorized access");
        }
    }

    // Deletes a deal
    public void deleteDeal(Long restaurantId, Long dealId) {
        Optional<LunchDeal> existingDeal = lunchDealRepository.findById(dealId);
        if (existingDeal.isPresent() && existingDeal.get().getRestaurantId().equals(restaurantId)) {
            lunchDealRepository.deleteById(dealId);
        } else {
            throw new IllegalArgumentException("Deal not found or unauthorized access");
        }
    }
}