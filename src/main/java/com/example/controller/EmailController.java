package com.example.controller;

import com.example.DTO.EmailRequest;
import com.example.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
@Tag(name = "Email API", description = "Endpoints to send notification emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value="/send", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Send Email", description = "Send an email notification to the specified recipient")
    public ResponseEntity<String> send(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }
}
