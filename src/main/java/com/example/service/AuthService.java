package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DTO.AuthResponse;
import com.example.DTO.LoginRequest;
import com.example.DTO.RegisterRequest;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.JWTUtil;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTUtil jwtUtil;
	
	
	 public AuthResponse register(RegisterRequest request) {
		 User user = new User();
		 user.setName(request.getName());
		 user.setEmail(request.getEmail());
		 user.setPassword(passwordEncoder.encode(request.getPassword()));
		 user.setRole(request.getRole());
		
		 userRepository.save(user);
		 
		 String token = jwtUtil.generateToken( user.getEmail(),user.getRole().name());
		 System.out.println(token);
		 return  new AuthResponse(token,"User Registerd Successful");
		  
	 }
	 
	 
	 public AuthResponse login(LoginRequest request) {
		
		 User user = userRepository.findByEmail(request.email).orElseThrow(() -> new RuntimeException("User not found"));
		 
		 if(!passwordEncoder.matches(request.password, user.getPassword())) {
			 System.out.println(request.password+" "+ user.getPassword());
			 throw new RuntimeException("Invalid credentials");}
		 
		 String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		 return new AuthResponse(token,"Login Successful");
		 
		 
	 }
	
}
