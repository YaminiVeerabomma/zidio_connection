package com.example.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.example.Enum.PaidStatus;

@Entity
@Table(name="user_payment_status")

public class UserPaymentStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long planId;
	private Long userId;
	private LocalDate subscriptionStart;
	private LocalDate subscriptionEnd;
	   @Enumerated(EnumType.STRING) // ✅ store enum as string in DB
	    @Column(name = "paid_status") // ✅ DB column will be `paid_status`
	    private PaidStatus paidStatus;
	private String transactionId;

	public UserPaymentStatus(){} 
	public UserPaymentStatus(Long id,Long planId,Long userId,LocalDate subscriptionStart,LocalDate subscriptionEnd,PaidStatus paidStatus,String transactionId) {
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