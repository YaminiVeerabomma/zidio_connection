package com.example.DTO;

import java.time.LocalDateTime;

public class InvoiceDTO {
    private Long id;
    private String userEmail;
    private String serviceType;
    private double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime purchaseDate;
    private String invoiceNumber;
    private String invoiceDownloadURL;
    private String subscriptionPlanName;

    // ✅ Getters & Setters
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
    public String getSubscriptionPlanName() { return subscriptionPlanName; }
    public void setSubscriptionPlanName(String subscriptionPlanName) { this.subscriptionPlanName = subscriptionPlanName; }
}
