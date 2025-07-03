package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recruiters")
public class Recruiter {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String companyName;
    private String phone;
    private String companydescription;
    private String companyWebsit;
    
    public Recruiter() {}

	public Recruiter(Long id, String name, String email, String companyName, String phone, String companydescription,
			String companyWebsit) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.companyName = companyName;
		this.phone = phone;
		this.companydescription = companydescription;
		this.companyWebsit = companyWebsit;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanydescription() {
		return companydescription;
	}

	public void setCompanydescription(String companydescription) {
		this.companydescription = companydescription;
	}

	public String getCompanyWebsit() {
		return companyWebsit;
	}

	public void setCompanyWebsit(String companyWebsit) {
		this.companyWebsit = companyWebsit;
	}
    
}

	