package com.example.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.Enum.Role;

public class AdminUserDTO {
	
	public Long id;

	  @NotBlank(message = "Name cannot be empty")
	    @Size(min = 2, max = 50, message = "Name must be between 2–50 characters")
	    public String name;

	    @NotBlank(message = "Email cannot be empty")
	    @Email(message = "Invalid email format")
	    public String email;

	    @NotNull(message = "Role must be provided")
	    public Role role;
	public boolean active;
	public boolean blocked;
	
	public AdminUserDTO() {}
	public AdminUserDTO(Long id,String name,String email,Role role,boolean active,boolean blocked) {
		
		this.id=id;
		this.name=name;
		this.email=email;
		this.role=role;
		this.active=active;
		this.blocked=blocked;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


}
