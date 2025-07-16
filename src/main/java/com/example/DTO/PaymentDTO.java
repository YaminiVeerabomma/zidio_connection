package com.example.DTO;

import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

	public Long id;
	public Long userId;
	public Long planId;
	public String transactionId;
	public BigDecimal amount;
	public String currency;
	public PaymentStatus paymentStatus;
	public PaymentType paymentType;
	public LocalDateTime paymentDate;

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

