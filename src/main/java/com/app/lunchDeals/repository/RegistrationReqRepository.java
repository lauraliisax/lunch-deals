package com.app.lunchDeals.repository;

import com.app.lunchDeals.entity.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationReqRepository extends JpaRepository<RegistrationRequest, Long> {
}