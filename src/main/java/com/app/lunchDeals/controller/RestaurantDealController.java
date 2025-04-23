package com.app.lunchDeals.controller;

import com.app.lunchDeals.entity.LunchDeal;
import com.app.lunchDeals.service.LunchDealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/deals")
public class RestaurantDealController {
    private final LunchDealService lunchDealService;

    public RestaurantDealController(LunchDealService lunchDealService) {
        this.lunchDealService = lunchDealService;
    }

    @PostMapping
    public LunchDeal createDeal(@PathVariable Long restaurantId, @RequestBody LunchDeal deal) {
        return lunchDealService.createDeal(restaurantId, deal);
    }

    @GetMapping
    public List<LunchDeal> getDeals(@PathVariable Long restaurantId) {
        return lunchDealService.getDealsByRestaurant(restaurantId);
    }

    @PutMapping("/{dealId}")
    public LunchDeal updateDeal(@PathVariable Long restaurantId, @PathVariable Long dealId, @RequestBody LunchDeal deal) {
        return lunchDealService.updateDeal(restaurantId, dealId, deal);
    }

    @DeleteMapping("/{dealId}")
    public void deleteDeal(@PathVariable Long restaurantId, @PathVariable Long dealId) {
        lunchDealService.deleteDeal(restaurantId, dealId);
    }
}