package com.example.controller;

import com.example.DTO.ApplicationDTO;
import com.example.Enum.Status;
import com.example.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@Tag(name = "Application API", description = "Endpoints to manage job applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicaionService;

    @PostMapping(value="/apply", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Apply for Job", description = "Create a new job application for a student")
    public ResponseEntity<ApplicationDTO> apply(@RequestBody ApplicationDTO dto) {
        return ResponseEntity.ok(applicaionService.apply(dto));
    }

    @GetMapping(value="/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Applications by Student ID", description = "Retrieve all applications submitted by a specific student")
    public ResponseEntity<List<ApplicationDTO>> getApplicationByStudentId(
            @Parameter(description = "ID of the student", required = true) @PathVariable Long studentId) {
        return ResponseEntity.ok(applicaionService.getApplicationByStudentId(studentId));
    }

    @GetMapping(value="/job/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Applications by Job ID", description = "Retrieve all applications for a specific job")
    public ResponseEntity<List<ApplicationDTO>> getApplicationByJobId(
            @Parameter(description = "ID of the job", required = true) @PathVariable Long jobId) {
        return ResponseEntity.ok(applicaionService.getApplicationByJobId(jobId));
    }

    @PostMapping(value="/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Application Status", description = "Update the status of a specific application")
    public void updateStatus(
            @Parameter(description = "ID of the application", required = true) @PathVariable Long id,
            @Parameter(description = "New status for the application", required = true) @RequestParam Status status) {
        applicaionService.updateStatus(id, status);
    }
}
