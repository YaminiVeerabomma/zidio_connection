package com.example.DTO;

import com.example.Enum.Designation;

public class RecruiterDTO {
    public Long id;
    public String name;
    public String email;
    public String companyName;
    public String phone;
    public String companyDescription;
    public String companyWebsite;
    public String companyAddress;
    public  Designation designation;
	


    public RecruiterDTO(Long id, String name, String email, String companyName, String phone, String companyDescription, String companyWebsite, String companyAddress,Designation designation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.phone = phone;
        this.companyDescription = companyDescription;
        this.companyWebsite = companyWebsite;
     //  this. designation= designation;
        this.companyAddress = companyAddress;
        this. designation= designation;
   

    }

	
    
}


