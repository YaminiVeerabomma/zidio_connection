package com.example.DTO;


public class StudentDTO {
	
	public Long id;
	public String name;
	public String email;
	public String phone;
	public String qualification;
	public String resumeURL;
	
	public StudentDTO(Long id2,String name2,String email2,String phone2,String qualification2,String resumeURL2) {
	this.id=id;
	this.name=name;
	this.email=email;
	this.phone=phone;
	this.qualification=qualification;
	this.resumeURL=resumeURL;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getResumeURL() {
		return resumeURL;
	}

	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}
	
	
}
