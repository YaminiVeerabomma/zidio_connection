package com.example.DTO;



import java.time.LocalDate;

import com.example.Enum.PaidStatus;
public class UserPaymentStatusDTO {
	
	public Long id;
	public Long planId;
	public Long userId;
	public LocalDate subscriptionStart;
	public LocalDate subscriptionEnd;
	public PaidStatus status;
	  public String transactionId;
	

	public UserPaymentStatusDTO() {}
	public UserPaymentStatusDTO(Long id,Long planId,Long userId,LocalDate subscriptionStart,LocalDate subscriptionEnd,PaidStatus status,   String transactionId) {
		this.id=id;
		this.planId=planId;
		this.userId=userId;
		this.subscriptionStart=subscriptionStart;
		this.subscriptionEnd=subscriptionEnd;
		this.status=status;
		this.transactionId=transactionId;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDate getSubscriptionStart() {
		return subscriptionStart;
	}
	public void setSubscriptionStart(LocalDate subscriptionStart) {
		this.subscriptionStart = subscriptionStart;
	}
	public LocalDate getSubscriptionEnd() {
		return subscriptionEnd;
	}
	public void setSubscriptionEnd(LocalDate subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}
	public PaidStatus getStatus() {
		return status;
	}
	public void setStatus(PaidStatus status) {
		this.status = status;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	

}