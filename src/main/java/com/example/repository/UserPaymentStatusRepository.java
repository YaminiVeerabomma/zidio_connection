package com.example.repository;

import com.example.entity.UserPaymentStatus;
import com.example.DTO.UserPaymentStatusDTO;
import com.example.Enum.PaidStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserPaymentStatusRepository extends JpaRepository<UserPaymentStatus, Long> {

    // ✅ Find by userId
    Optional<UserPaymentStatus> findByUserId(Long userId);

    // ✅ Find by planId
    List<UserPaymentStatus> findByPlanId(Long planId);

    // ✅ Find all by status
    List<UserPaymentStatus> findByPaidStatus(PaidStatus PaidStatus);

    // ✅ Active subscriptions (where subscriptionEnd >= today)
    List<UserPaymentStatus> findBySubscriptionEndGreaterThanEqual(LocalDate today);

    // ✅ Expired subscriptions (where subscriptionEnd < today)
    List<UserPaymentStatus> findBySubscriptionEndBefore(LocalDate today);



    // ✅ Delete by userId
    void deleteByUserId(Long userId);

    List<UserPaymentStatus> findBySubscriptionEndAfter(LocalDate date);

    boolean existsByUserId(Long userId);


}
