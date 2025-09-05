package com.example.DTO;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.Enum.Role;

public class RegisterRequest {
	public Long id;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    public  String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    public  String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    // At least 1 uppercase, 1 lowercase, 1 digit, 1 special char
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
        message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character"
    )
    public String password;

    @NotNull(message = "Role is required")
    public  Role role;



    public RegisterRequest() {}
    
    public RegisterRequest(Long id,String name, String email, String password, Role role) {
    	this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	
}
