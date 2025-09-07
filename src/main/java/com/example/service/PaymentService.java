package com.example.service;

import com.example.DTO.PaymentDTO;
import com.example.Enum.PaidStatus;
import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;
import com.example.entity.Payment;
import com.example.entity.UserPaymentStatus;
import com.example.exception.PaymentNotFoundException;
import com.example.exception.PaymentProcessingException;
import com.example.repository.PaymentRepository;
import com.example.repository.UserPaymentStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserPaymentStatusRepository userPaymentStatusRepository;

    // ---------------- MAKE PAYMENT ----------------
    public PaymentDTO makePayment(PaymentDTO dto) {
        log.info("💳 makePayment called for userId={} planId={} amount={}", dto.getUserId(), dto.getPlanId(), dto.getAmount());

        try {
            Payment pay = new Payment();
            pay.setUserId(dto.getUserId());
            pay.setPlanId(dto.getPlanId());
            pay.setAmount(dto.getAmount());
            pay.setCurrency(dto.getCurrency());
            pay.setPaymentType(dto.getPaymentType());
            pay.setPaymentStatus(dto.getPaymentStatus());
            pay.setPaymentDate(dto.getPaymentDate());
            pay.setTransactionId(dto.getTransactionId());

            Payment saved = paymentRepository.save(pay);
            log.info("✅ Payment saved successfully with transactionId={}", saved.getTransactionId());

            if (saved.getPaymentStatus() == PaymentStatus.PAYMENTSUCCESSFUL) {
                UserPaymentStatus subscription = new UserPaymentStatus();
                subscription.setUserId(saved.getUserId());
                subscription.setPlanId(saved.getPlanId());
                subscription.setTransactionId(saved.getTransactionId());
                subscription.setSubscriptionStart(LocalDate.now());
                subscription.setSubscriptionEnd(LocalDate.now().plusDays(30)); // Example: 30 days validity
                subscription.setPaidStatus(PaidStatus.PENDING);

                userPaymentStatusRepository.save(subscription);
                log.info("📅 User subscription updated for userId={} planId={}", saved.getUserId(), saved.getPlanId());
            }

            dto.setId(saved.getId());
            dto.setPaymentDate(saved.getPaymentDate());
            dto.setPaymentStatus(saved.getPaymentStatus());

            return dto;
        } catch (Exception e) {
            log.error("❌ Error processing payment for userId={} planId={}", dto.getUserId(), dto.getPlanId(), e);
            throw new PaymentProcessingException("Error occurred while processing payment: " + e.getMessage());
        }
    }

    // ---------------- GET ALL PAYMENTS ----------------
    public List<PaymentDTO> getAllPayments() {
        log.info("📚 getAllPayments called");

        List<PaymentDTO> payments = paymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        log.info("✅ Total payments fetched: {}", payments.size());
        return payments;
    }

    // ---------------- GET PAYMENT BY ID ----------------
    public PaymentDTO getPaymentById(Long id) {
        log.info("🔍 getPaymentById called for id={}", id);

        return paymentRepository.findById(id)
                .map(p -> {
                    log.info("✅ Payment found with transactionId={}", p.getTransactionId());
                    return mapToDTO(p);
                })
                .orElseThrow(() -> {
                    log.warn("⚠️ Payment not found with id={}", id);
                    return new PaymentNotFoundException("Payment not found with id: " + id);
                });
    }

    // ---------------- GET PAYMENT BY TRANSACTION ID ----------------
    public PaymentDTO getPaymentByTransactionId(String transactionId) {
        log.info("🔎 getPaymentByTransactionId called for transactionId={}", transactionId);

        return paymentRepository.findByTransactionId(transactionId)
                .map(p -> {
                    log.info("✅ Payment found for transactionId={}", transactionId);
                    return mapToDTO(p);
                })
                .orElseThrow(() -> {
                    log.warn("⚠️ Payment not found with transactionId={}", transactionId);
                    return new PaymentNotFoundException("Payment not found with transactionId: " + transactionId);
                });
    }

    // ---------------- GET PAYMENTS BY USER ----------------
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        log.info("👤 getPaymentsByUserId called for userId={}", userId);

        List<PaymentDTO> payments = paymentRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (payments.isEmpty()) {
            log.warn("⚠️ No payments found for userId={}", userId);
            throw new PaymentNotFoundException("No payments found for userId: " + userId);
        }

        log.info("✅ {} payment(s) found for userId={}", payments.size(), userId);
        return payments;
    }

    // ---------------- GET PAYMENTS BY PLAN ----------------
    public List<PaymentDTO> getPaymentsByPlanId(Long planId) {
        log.info("📦 getPaymentsByPlanId called for planId={}", planId);

        List<PaymentDTO> payments = paymentRepository.findByPlanId(planId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (payments.isEmpty()) {
            log.warn("⚠️ No payments found for planId={}", planId);
            throw new PaymentNotFoundException("No payments found for planId: " + planId);
        }

        log.info("✅ {} payment(s) found for planId={}", payments.size(), planId);
        return payments;
    }

    // ---------------- GET PAYMENTS BY PLAN AND STATUS ----------------
    public List<PaymentDTO> getPaymentsByPlanIdAndStatus(Long planId, PaymentStatus status) {
        log.info("📊 getPaymentsByPlanIdAndStatus called for planId={} status={}", planId, status);

        List<PaymentDTO> payments = paymentRepository.findByPlanIdAndPaymentStatus(planId, status)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (payments.isEmpty()) {
            log.warn("⚠️ No payments found for planId={} with status={}", planId, status);
            throw new PaymentNotFoundException("No payments found for planId " + planId + " with status: " + status);
        }

        log.info("✅ {} payment(s) found for planId={} with status={}", payments.size(), planId, status);
        return payments;
    }

    // ---------------- GET PAYMENTS BY USER AND TYPE ----------------
    public List<PaymentDTO> getPaymentsByUserAndType(Long userId, PaymentType type) {
        log.info("💳 getPaymentsByUserAndType called for userId={} type={}", userId, type);

        List<PaymentDTO> payments = paymentRepository.findByUserIdAndPaymentType(userId, type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (payments.isEmpty()) {
            log.warn("⚠️ No payments found for userId={} and type={}", userId, type);
            throw new PaymentNotFoundException("No payments found for userId " + userId + " with type: " + type);
        }

        log.info("✅ {} payment(s) found for userId={} and type={}", payments.size(), userId, type);
        return payments;
    }

    // ---------------- DELETE PAYMENT ----------------
    public boolean deletePayment(Long id) {
        log.info("🗑️ deletePayment called for id={}", id);

        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            log.info("✅ Payment deleted successfully with id={}", id);
            return true;
        }

        log.warn("⚠️ Payment not found for deletion with id={}", id);
        throw new PaymentNotFoundException("Payment not found for deletion with id: " + id);
    }

    // ---------------- MAP ENTITY TO DTO ----------------
    private PaymentDTO mapToDTO(Payment p) {
        log.debug("🔧 mapToDTO called for paymentId={}", p.getId());

        PaymentDTO dto = new PaymentDTO();
        dto.setId(p.getId());
        dto.setUserId(p.getUserId());
        dto.setPlanId(p.getPlanId());
        dto.setAmount(p.getAmount());
        dto.setCurrency(p.getCurrency());
        dto.setPaymentStatus(p.getPaymentStatus());
        dto.setPaymentType(p.getPaymentType());
        dto.setPaymentDate(p.getPaymentDate());
        dto.setTransactionId(p.getTransactionId());
        return dto;
    }
}
