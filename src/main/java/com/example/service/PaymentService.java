package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.PaymentDTO;
import com.example.Enum.PaidStatus;
import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;
import com.example.entity.Payment;
import com.example.entity.UserPaymentStatus;
import com.example.repository.PaymentRepository;
import com.example.repository.UserPaymentStatusRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserPaymentStatusRepository userPaymentStatusRepository;

    // ✅ Create or make payment (and update subscription)
    public PaymentDTO makePayment(PaymentDTO dto) {
        Payment pay = new Payment();
        pay.setUserId(dto.getUserId());
        pay.setPlanId(dto.getPlanId());
        pay.setAmount(dto.getAmount());
        pay.setCurrency(dto.getCurrency());
        pay.setPaymentType(dto.getPaymentType());
        pay.setPaymentStatus(dto.getPaymentStatus());
        pay.setPaymentDate(dto.getPaymentDate());
        pay.setTransactionId(dto.getTransactionId());

        // Save Payment
        Payment saved = paymentRepository.save(pay);

        // Update UserPaymentStatus if COMPLETED
        if (saved.getPaymentStatus() == PaymentStatus.PAYMENTSUCCESSFUL) {
            UserPaymentStatus subscription = new UserPaymentStatus();
            subscription.setUserId(saved.getUserId());
            subscription.setPlanId(saved.getPlanId());
            subscription.setTransactionId(saved.getTransactionId());
            subscription.setSubscriptionStart(LocalDate.now());
            subscription.setSubscriptionEnd(LocalDate.now().plusDays(30)); // Example: 30 days validity
            subscription.setPaidStatus(PaidStatus.ACTIVE);

            userPaymentStatusRepository.save(subscription);
        }

        // Map back to DTO
        dto.setId(saved.getId());
        dto.setPaymentDate(saved.getPaymentDate());
        dto.setPaymentStatus(saved.getPaymentStatus());
        return dto;
    }

    // ✅ Get all payments
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get payment by ID
    public PaymentDTO getPaymentById(Long id) {
        return paymentRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    // ✅ Get payment by transaction ID
    public PaymentDTO getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId).map(this::mapToDTO).orElse(null);
    }

    // ✅ Get payments by user
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get payments by plan
    public List<PaymentDTO> getPaymentsByPlanId(Long planId) {
        return paymentRepository.findByPlanId(planId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get payments by plan and status
    public List<PaymentDTO> getPaymentsByPlanIdAndStatus(Long planId, PaymentStatus status) {
        return paymentRepository.findByPlanIdAndPaymentStatus(planId, status).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get payments by user and type
    public List<PaymentDTO> getPaymentsByUserAndType(Long userId, PaymentType type) {
        return paymentRepository.findByUserIdAndPaymentType(userId, type).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Delete payment
    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method to map entity → DTO
    private PaymentDTO mapToDTO(Payment p) {
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
