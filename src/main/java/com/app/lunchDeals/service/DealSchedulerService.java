package com.app.lunchDeals.service;

import com.app.lunchDeals.entity.LunchDeal;
import com.app.lunchDeals.repository.LunchDealRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DealSchedulerService {
    private final LunchDealRepository lunchDealRepository;

    public DealSchedulerService(LunchDealRepository lunchDealRepository) {
        this.lunchDealRepository = lunchDealRepository;
    }

    // Runs every day at midnight to activate scheduled deals
    @Scheduled(cron = "0 0 0 * * ?")
    public void activateScheduledDeals() {
        LocalDate today = LocalDate.now();
        List<LunchDeal> scheduledDeals = lunchDealRepository.findByDateAndStatus(today, "SCHEDULED");
        for (LunchDeal deal : scheduledDeals) {
            deal.setStatus("ACTIVE");
            lunchDealRepository.save(deal);
        }
    }
}