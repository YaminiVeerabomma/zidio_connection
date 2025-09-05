package com.example.DTO;

import javax.validation.constraints.Email;

public class ForgotPasswordRequest {

    @Email(message = "Invalid email format")
    public  String email;

    public ForgotPasswordRequest() {
        // no-arg constructor for Jackson
    }

    public ForgotPasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
