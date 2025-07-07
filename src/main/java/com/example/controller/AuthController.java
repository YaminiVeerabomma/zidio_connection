package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AuthResponse;
import com.example.DTO.LoginRequest;
import com.example.DTO.RegisterRequest;
import com.example.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        
        return ResponseEntity.ok(response);
    }
    
    
    @PostMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
	
	    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
	        return ResponseEntity.ok(authService.login(request));
	    }

}
//adding **produces = MediaType.APPLICATION_JSON_VALUE** in your controller method ensures that the response will be returned in JSON format.
//if add the above line we can import 


