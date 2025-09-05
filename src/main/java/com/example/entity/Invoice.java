package com.example.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private String serviceType;
    private double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime purchaseDate = LocalDateTime.now();
    private String invoiceNumber;
    private String invoiceDownloadURL;

    // ✅ Correct mapping: Link to SubscriptionPlan entity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_plan_id", nullable = false)
    private SubscriptionPlan subscriptionPlan;

    public Invoice() {}

    public Invoice(String userEmail, String serviceType, double amount,
                   String paymentMethod, String status,
                   String invoiceNumber, String invoiceDownloadURL,
                   SubscriptionPlan subscriptionPlan) {
        this.userEmail = userEmail;
        this.serviceType = serviceType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDownloadURL = invoiceDownloadURL;
        this.subscriptionPlan = subscriptionPlan;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }

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

    public SubscriptionPlan getSubscriptionPlan() { return subscriptionPlan; }
    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) { this.subscriptionPlan = subscriptionPlan; }
}
