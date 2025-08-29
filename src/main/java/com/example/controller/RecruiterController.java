package com.example.controller;

import com.example.DTO.RecruiterDTO;
import com.example.DTO.StudentDTO;
import com.example.Enum.Designation;
import com.example.entity.Recruiter;
import com.example.service.RecruiterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    // 🔹 Create Recruiter
    @PostMapping("/{userId}")
    public ResponseEntity<RecruiterDTO> createRecruiter(
            @PathVariable Long userId,
            @RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.saveOrUpdateRecruiter(dto, userId));
    }

    // 🔹 Update Recruiter
    @PutMapping("/{userId}")
    public ResponseEntity<RecruiterDTO> updateRecruiter(
            @PathVariable Long userId,
            @RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.saveOrUpdateRecruiter(dto, userId));
    }

    // 🔹 Get Recruiter by ID
    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDTO> getRecruiterById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }

    // 🔹 Get All Recruiters
    @GetMapping
    public ResponseEntity<List<RecruiterDTO>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    // 🔹 Delete Recruiter
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.ok("Recruiter deleted successfully!");
    }

    // 🔹 Get by Designation
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<RecruiterDTO>> getRecruitersByDesignation(
            @PathVariable Designation designation) {
        return ResponseEntity.ok(recruiterService.getRecruitersByDesignation(designation));
    }
}
