package com.example.DTO;

import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

public class PaymentDTO {

	public Long id;
    @NotNull(message = "User ID cannot be null")
	public Long userId;
    @NotNull(message = "Plan ID cannot be null")
    public  Long planId;

    @NotBlank(message = "Transaction ID cannot be blank")
    public  String transactionId;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    public  BigDecimal amount;

    @NotBlank(message = "Currency cannot be blank")
    @Size(min = 3, max = 3, message = "Currency must be a valid 3-letter ISO code (e.g., USD, INR)")
    public  String currency;

    @NotNull(message = "Payment status cannot be null")
    public  PaymentStatus paymentStatus;

    @NotNull(message = "Payment type cannot be null")
    public  PaymentType paymentType;

    @PastOrPresent(message = "Payment date cannot be in the future")
    public  LocalDateTime paymentDate;

    // No-argument constructor
    public PaymentDTO() {
    }

    // All-argument constructor
    public PaymentDTO(Long id, Long userId, Long planId, String transactionId, BigDecimal amount,
                      String currency, PaymentStatus paymentStatus, PaymentType paymentType,
                      LocalDateTime paymentDate) {
        this.id = id;
        this.userId = userId;
        this.planId = planId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}

