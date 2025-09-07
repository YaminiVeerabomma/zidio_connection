package com.example.repository;

import com.example.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    // Custom query methods
    List<SubscriptionPlan> findByPrice(Double price);

    List<SubscriptionPlan> findByDurationInDays(Integer durationInDays);

    List<SubscriptionPlan> findByPriceAndDurationInDays(Double price, Integer durationInDays);

	Optional<SubscriptionPlan> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword,
			String keyword2);
    
}
