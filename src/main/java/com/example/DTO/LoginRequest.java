package com.example.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	public String email;
	
	@NotBlank(message = "Password is required")
	public String password;
	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
