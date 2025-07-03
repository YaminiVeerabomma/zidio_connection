package com.example.DTO;

import com.example.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import com.example.Enum.Role;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {
	
	
	public String name;
	public String email;
	public String password;
	public Role role;
	

}
