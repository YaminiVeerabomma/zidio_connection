package com.example.DTO;

public class SubscriptionPlanDTO {

	
	public Long id;
	public String name;
	public Double price;
	public String description;
	public Integer durationInDays;
	public String rozorpayorderId;
	public String rozorpayPaymentId;
	
	
	public SubscriptionPlanDTO() {}
	
	public SubscriptionPlanDTO(Long id,String name,String description,Double price,Integer durationInDays,String rozorpayorderId, String  rozorpayPaymentId) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.price=price;
		this.durationInDays=durationInDays;
		this.rozorpayorderId=rozorpayorderId;
		this. rozorpayPaymentId= rozorpayPaymentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(Integer durationInDays) {
		this.durationInDays = durationInDays;
	}

	public String getRozorpayorderId() {
		return rozorpayorderId;
	}

	public void setRozorpayorderId(String rozorpayorderId) {
		this.rozorpayorderId = rozorpayorderId;
	}

	public String getRozorpayPaymentId() {
		return rozorpayPaymentId;
	}

	public void setRozorpayPaymentId(String rozorpayPaymentId) {
		this.rozorpayPaymentId = rozorpayPaymentId;
	}
	
}