package com.example.DTO;

import lombok.Builder;

@Builder
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

}
