package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
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
	@ManyToOne
    @JoinColumn(name = "subscription_plan_id") // This will be the foreign key column
	private SubscriptionPlan subscriptionPlan;
	public Invoice() {}

	public Invoice(Long id, String userEmail, String serviceType, double amount, String paymentMethod, String status,
			LocalDateTime purchaseDate,String invoiceNumber,String invoiceDownloadURL,SubscriptionPlan subscriptionPlan) {
		
		this.id = id;
		this.userEmail = userEmail;
		this.serviceType = serviceType;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.purchaseDate = purchaseDate;
		this.invoiceNumber=invoiceNumber;
		this.invoiceDownloadURL=invoiceNumber;
		this.subscriptionPlan=subscriptionPlan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDownloadURL() {
		return invoiceDownloadURL;
	}

	public void setInvoiceDownloadURL(String invoiceDownloadURL) {
		this.invoiceDownloadURL = invoiceDownloadURL;
	}

	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}
	
    

   
}
