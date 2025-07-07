package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.RecruiterDTO;
import com.example.DTO.StudentDTO;
import com.example.service.RecruiterService;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {
	
    @Autowired
    private RecruiterService recruiterService;
   
    @PostMapping(value="/Register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <RecruiterDTO> register(@RequestBody RecruiterDTO dto){
    	   System.out.println("Register API hit");
    	 return ResponseEntity.ok(recruiterService.createrRecruiter(dto));
    }
    @GetMapping(value="/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecruiterDTO> getRecruiterByEmail(@PathVariable String email) {
        return ResponseEntity.ok(recruiterService.getRecruiterByEmail(email));
    }
    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecruiterDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }
    
}
