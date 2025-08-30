package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Payment;
import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long userId);

    List<Payment> findByPlanId(Long planId);

    List<Payment> findByPlanIdAndPaymentStatus(Long planId, PaymentStatus status);

    List<Payment> findByUserIdAndPaymentType(Long userId, PaymentType type);

    Optional<Payment> findByTransactionId(String transactionId);
}
