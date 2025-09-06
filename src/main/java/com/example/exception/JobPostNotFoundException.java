package com.example.exception;

public class JobPostNotFoundException extends RuntimeException {
    public JobPostNotFoundException(Long id) {
        super("Job Post not found with id: " + id);
    }
    
    public JobPostNotFoundException(String message) {
        super(message);
    }
}