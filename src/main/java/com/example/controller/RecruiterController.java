package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.RecruiterDTO;
import com.example.Enum.Designation;
import com.example.service.RecruiterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/api/recruiter")
@Tag(name = "Recruiter API", description = "Operations related to Recruiter management")
public class RecruiterController {
	
    @Autowired
    private RecruiterService recruiterService;

    @PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Register Recruiter",
        description = "Create a new recruiter account"
           )
    public ResponseEntity<RecruiterDTO> register(@RequestBody RecruiterDTO dto){
        return ResponseEntity.ok(recruiterService.createrRecruiter(dto));
    }

    @GetMapping(value="/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get Recruiter by Email",
        description = "Fetch a recruiter by their email",
        parameters = {
            @Parameter(name = "email", description = "Email of the recruiter", required = true, example = "alice@example.com")
        }
    )
    public ResponseEntity<RecruiterDTO> getRecruiterByEmail(@PathVariable String email) {
        return ResponseEntity.ok(recruiterService.getRecruiterByEmail(email));
    }

    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get Recruiter by ID",
        description = "Fetch a recruiter by their unique ID",
        parameters = {
            @Parameter(name = "id", description = "ID of the recruiter", required = true, example = "1")
        }
    )
    public ResponseEntity<RecruiterDTO> getRecruiterById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }

    @GetMapping(value="/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get Recruiters by Designation",
        description = "Fetch all recruiters with a specific designation",
        parameters = {
            @Parameter(name = "designation", description = "Designation enum value", required = true, example = "HR_MANAGER")
        }
    )
    public List<RecruiterDTO> getRecruitersByDesignation(@PathVariable Designation designation) {
        return recruiterService.getRecruitersByDesignation(designation);
    }
}
