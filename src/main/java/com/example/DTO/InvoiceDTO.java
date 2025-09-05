package com.example.DTO;


import java.time.LocalDateTime;
import java.time.LocalDateTime.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

public class InvoiceDTO {

    private Long id;

    @NotBlank(message = "User email is required")
    @Email(message = "Invalid email format")
    private String userEmail;

    @NotBlank(message = "Service type is required")
    private String serviceType;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotBlank(message = "Status is required")
    private String status;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDateTime purchaseDate;

    @NotBlank(message = "Invoice number is required")
    private String invoiceNumber;

    private String invoiceDownloadURL; // optional, no validation

    // ✅ Instead of exposing SubscriptionPlan entity, only expose required fields
    @NotNull(message = "Subscription Plan ID is required")
    private Long subscriptionPlanId;

    @NotBlank(message = "Subscription Plan name is required")
    private String subscriptionPlanName;

    @Positive(message = "Subscription price must be positive")
    private Double subscriptionPlanPrice;

    @Positive(message = "Duration must be greater than 0")
    private Integer subscriptionPlanDuration;

    // 👉 Constructors
    public InvoiceDTO() {}

    public InvoiceDTO(Long id, String userEmail, String serviceType, double amount,
                      String paymentMethod, String status, LocalDateTime purchaseDate,
                      String invoiceNumber, String invoiceDownloadURL,
                      Long subscriptionPlanId, String subscriptionPlanName,
                      Double subscriptionPlanPrice, Integer subscriptionPlanDuration) {
        this.id = id;
        this.userEmail = userEmail;
        this.serviceType = serviceType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDownloadURL = invoiceDownloadURL;
        this.subscriptionPlanId = subscriptionPlanId;
        this.subscriptionPlanName = subscriptionPlanName;
        this.subscriptionPlanPrice = subscriptionPlanPrice;
        this.subscriptionPlanDuration = subscriptionPlanDuration;
    }

    // 👉 Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getInvoiceDownloadURL() { return invoiceDownloadURL; }
    public void setInvoiceDownloadURL(String invoiceDownloadURL) { this.invoiceDownloadURL = invoiceDownloadURL; }

    public Long getSubscriptionPlanId() { return subscriptionPlanId; }
    public void setSubscriptionPlanId(Long subscriptionPlanId) { this.subscriptionPlanId = subscriptionPlanId; }

    public String getSubscriptionPlanName() { return subscriptionPlanName; }
    public void setSubscriptionPlanName(String subscriptionPlanName) { this.subscriptionPlanName = subscriptionPlanName; }

    public Double getSubscriptionPlanPrice() { return subscriptionPlanPrice; }
    public void setSubscriptionPlanPrice(Double subscriptionPlanPrice) { this.subscriptionPlanPrice = subscriptionPlanPrice; }

    public Integer getSubscriptionPlanDuration() { return subscriptionPlanDuration; }
    public void setSubscriptionPlanDuration(Integer subscriptionPlanDuration) { this.subscriptionPlanDuration = subscriptionPlanDuration; }
}
