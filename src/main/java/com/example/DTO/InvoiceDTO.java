package com.example.DTO;

import java.time.LocalDateTime;

import com.example.entity.SubscriptionPlan;

public class InvoiceDTO {
	  public String userEmail;
	  public String serviceType;
	  public double amount;
	  public String paymentMethod;
	  public String status;

	  public LocalDateTime purchaseDate = LocalDateTime.now();
	  public String invoiceId;
	  public String invoiceDownloadURL;
	   public SubscriptionPlan subscriptionPlan;
		public InvoiceDTO() {}
	public InvoiceDTO(String userEmail, String serviceType, double amount, String paymentMethod, String status,
			LocalDateTime purchaseDate,String invoiceId,String invoiceDownloadURL ,SubscriptionPlan subscriptionPlan) {
		
		this.userEmail = userEmail;
		this.serviceType = serviceType;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.purchaseDate = purchaseDate;
		this.invoiceId=invoiceId;
		this.invoiceDownloadURL=invoiceDownloadURL;
		this.subscriptionPlan=subscriptionPlan;
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
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceDownloadUrl() {
		return invoiceDownloadURL;
	}
	public void setInvoiceDownloadUrl(String invoiceDownloadUrl) {
		this.invoiceDownloadURL = invoiceDownloadURL;
	}
	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}
	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}
	
  

}
