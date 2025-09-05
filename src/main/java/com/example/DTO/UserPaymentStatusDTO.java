package com.example.DTO;



import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.Enum.PaidStatus;
public class UserPaymentStatusDTO {
	
	public Long id;
	@NotNull(message = "Plan ID cannot be null")
    public Long planId;

    @NotNull(message = "User ID cannot be null")
    public Long userId;

    @NotNull(message = "Subscription start date cannot be null")
    public LocalDate subscriptionStart;

    @NotNull(message = "Subscription end date cannot be null")
    @Future(message = "Subscription end date must be in the future")
    public LocalDate subscriptionEnd;

    @NotNull(message = "Paid status is required")
    public PaidStatus paidStatus;

    @NotBlank(message = "Transaction ID cannot be blank")
    public String transactionId;

	public UserPaymentStatusDTO() {}
	public UserPaymentStatusDTO(Long id,Long planId,Long userId,LocalDate subscriptionStart,LocalDate subscriptionEnd,PaidStatus paidstatus,   String transactionId) {
		this.id=id;
		this.planId=planId;
		this.userId=userId;
		this.subscriptionStart=subscriptionStart;
		this.subscriptionEnd=subscriptionEnd;
		this.paidStatus=paidStatus;
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
	
	public PaidStatus getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(PaidStatus paidStatus) {
		this.paidStatus = paidStatus;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	

}