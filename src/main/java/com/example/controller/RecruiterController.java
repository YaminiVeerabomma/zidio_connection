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
@Tag(name = "Recruiter API", description = "APIs for managing recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    // 🔹 Create Recruiter
    @PostMapping("/{userId}")
    @Operation(summary = "Create recruiter", description = "Creates a recruiter linked to a given userId")
    public ResponseEntity<RecruiterDTO> createRecruiter(@PathVariable Long userId, @RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.saveRecruiter(dto, userId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(
            @PathVariable Long id,
            @RequestBody Recruiter recruiter) {
        Recruiter updated = recruiterService.updateRecruiter(id, recruiter);
        return ResponseEntity.ok(updated);
    }
    // 🔹 Get Recruiter by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get recruiter by ID", description = "Fetch a recruiter by recruiter ID")
    public ResponseEntity<RecruiterDTO> getRecruiterById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }

    // 🔹 Get All Recruiters
    @GetMapping
    @Operation(summary = "Get all recruiters", description = "Fetch all recruiters")
    public ResponseEntity<List<RecruiterDTO>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    // 🔹 Delete Recruiter
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recruiter", description = "Delete a recruiter by ID")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.ok("Recruiter deleted successfully!");
    }
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<RecruiterDTO>> getRecruitersByDesignation(@PathVariable Designation designation) {
        return ResponseEntity.ok(recruiterService.getRecruitersByDesignation(designation));
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<RecruiterDTO>> getRecruitersBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(recruiterService.getRecruitersBySkill(skill));
    }
}
