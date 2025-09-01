package com.example.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.EmailNotRegisteredException;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.InvalidEmailException;
import com.example.exception.UserNotFoundException;
import com.example.exception.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ----- LOGIN / AUTH ERRORS -----
    @ExceptionHandler({UserNotFoundException.class, InvalidCredentialsException.class})
    public ResponseEntity<Map<String, String>> handleAuthExceptions() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Invalid login credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // ----- STUDENT NOT FOUND -----
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleStudentNotFound(StudentNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage()); // e.g. "Student not found with id 5"
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // ----- VALIDATION ERRORS -----
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // ----- GENERIC 400 ERROR -----
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({InvalidEmailException.class, EmailNotRegisteredException.class})
    public ResponseEntity<Map<String, String>> handleEmailExceptions(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ----- FALLBACK 500 ERROR -----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOtherExceptions(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
