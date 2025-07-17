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
	  

}
