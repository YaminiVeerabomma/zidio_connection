package com.example.DTO;

import java.time.LocalDateTime;

public class InvoiceDTO {
	  public String userEmail;
	  public String serviceType;
	  public double amount;
	  public String paymentMethod;
	  public String status;

	  public LocalDateTime purchaseDate = LocalDateTime.now();
		public InvoiceDTO() {}
	public InvoiceDTO(String userEmail, String serviceType, double amount, String paymentMethod, String status,
			LocalDateTime purchaseDate) {
		
		this.userEmail = userEmail;
		this.serviceType = serviceType;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.purchaseDate = purchaseDate;
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
  

}
