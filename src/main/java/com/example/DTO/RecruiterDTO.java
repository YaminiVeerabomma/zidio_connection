package com.example.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.Enum.Designation;

public class RecruiterDTO {
    public Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2–50 characters")
    public String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    public String email;

    @NotBlank(message = "Company name cannot be blank")
    public String companyName;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    public String phone;

    @Size(max = 500, message = "Company description cannot exceed 500 characters")
    public String companyDescription;

    @Pattern(regexp = "^(http|https)://.*$", message = "Website must be a valid URL")
    public String companyWebsite;

    @NotBlank(message = "Company address cannot be blank")
    public String companyAddress;

    public Designation designation;
	


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


