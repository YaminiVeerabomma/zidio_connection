package com.example.advice;



import java.time.LocalDateTime;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.DTO.ApiError;
import com.example.exception.EmailAlreadyExistsException;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.InvalidResetTokenException;
import com.example.exception.JobPostNotFoundException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.TokenExpiredException;
import com.example.exception.UserNotFoundException;
import com.example.exception.UserPaymentStatusNotFoundException;
import com.example.exception. PaymentProcessingException;
import com.example.exception.PaymentNotFoundException;
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
        UserNotFoundException.class, HttpStatus.NOT_FOUND,
        ResourceNotFoundException.class, HttpStatus.NOT_FOUND,
        EmailAlreadyExistsException.class, HttpStatus.CONFLICT,
        InvalidCredentialsException.class, HttpStatus.UNAUTHORIZED,
        InvalidResetTokenException.class, HttpStatus.BAD_REQUEST,
        TokenExpiredException.class, HttpStatus.GONE,
        JobPostNotFoundException.class ,HttpStatus.NOT_FOUND,
        UserPaymentStatusNotFoundException.class,HttpStatus.NOT_FOUND,
        PaymentNotFoundException.class, HttpStatus.NOT_FOUND,   
        PaymentProcessingException.class, HttpStatus.INTERNAL_SERVER_ERROR 
        
    );

    @ExceptionHandler({
        UserNotFoundException.class,
        ResourceNotFoundException.class,
        EmailAlreadyExistsException.class,
        InvalidCredentialsException.class,
        InvalidResetTokenException.class,
        TokenExpiredException.class,
        JobPostNotFoundException.class,
        UserPaymentStatusNotFoundException.class,
        PaymentNotFoundException.class,
        PaymentProcessingException.class
        
    })
    public ResponseEntity<ApiError> handleKnownExceptions(Exception ex, WebRequest request) {
        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return buildResponse(status, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnknownException(Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage(), request);
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, WebRequest request) {
        ApiError error = new ApiError(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            message,
            request.getDescription(false)
        );
        return new ResponseEntity<>(error, status);
    }
}

//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Handle User Not Found
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
//        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
//    }
//
//    // Handle Email Already Exists
//    @ExceptionHandler(EmailAlreadyExistsException.class)
//    public ResponseEntity<ApiError> handleEmailExists(EmailAlreadyExistsException ex, WebRequest request) {
//        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
//    }
//
//    // Handle Invalid Credentials
//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<ApiError> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {
//        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
//    }
//
//    // Handle Invalid Reset Token
//    @ExceptionHandler(InvalidResetTokenException.class)
//    public ResponseEntity<ApiError> handleInvalidToken(InvalidResetTokenException ex, WebRequest request) {
//        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
//    }
//
//    // Handle Token Expired
//    @ExceptionHandler(TokenExpiredException.class)
//    public ResponseEntity<ApiError> handleExpiredToken(TokenExpiredException ex, WebRequest request) {
//        return buildResponse(HttpStatus.GONE, ex.getMessage(), request);
//    }
//    
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
//        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
//    }
//
//
//    // Fallback for all other exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleGeneral(Exception ex, WebRequest request) {
//        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage(), request);
//    }
//
//    // Utility method to build response
//    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, WebRequest request) {
//        ApiError error = new ApiError(
//                LocalDateTime.now(),
//                status.value(),
//                status.getReasonPhrase(),
//                message,
//                request.getDescription(false) // e.g. "uri=/api/users/1"
//        );
//        return new ResponseEntity<>(error, status);
//    }
//}
