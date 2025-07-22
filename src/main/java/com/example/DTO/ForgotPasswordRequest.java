package com.example.DTO;

public class ForgotPasswordRequest {
    private String email;

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
