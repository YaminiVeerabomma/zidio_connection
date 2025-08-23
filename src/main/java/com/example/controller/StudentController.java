package com.example.controller;

import com.example.DTO.StudentDTO;
import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;
import com.example.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student API", description = "CRUD + Filter operations for Students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ---------- CRUD ----------
    @Operation(summary = "Create or Update a Student (link with User)")
    @PostMapping("/{userId}")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO dto, @PathVariable Long userId) {
        return ResponseEntity.ok(studentService.saveStudent(dto, userId));
    }

    @Operation(summary = "Get Student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get All Students")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Delete Student by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    // ---------- Filters ----------
    @Operation(summary = "Filter Students by Gender")
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<StudentDTO>> getByGender(@PathVariable Gender gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }

    @Operation(summary = "Filter Students by Experience Level")
    @GetMapping("/experience/{level}")
    public ResponseEntity<List<StudentDTO>> getByExperience(@PathVariable ExperienceLevel level) {
        return ResponseEntity.ok(studentService.getByExperienceLevel(level));
    }

    @Operation(summary = "Filter Students by Graduation Year")
    @GetMapping("/graduation/{year}")
    public ResponseEntity<List<StudentDTO>> getByGraduationYear(@PathVariable Integer year) {
        return ResponseEntity.ok(studentService.getByGraduationYear(year));
    }

    @Operation(summary = "Filter Students by Skill")
    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<StudentDTO>> getBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(studentService.getBySkill(skill));
    }

    @Operation(summary = "Filter Students by Notice Period")
    @GetMapping("/notice/{notice}")
    public ResponseEntity<List<StudentDTO>> getByNotice(@PathVariable NoticePeriod notice) {
        return ResponseEntity.ok(studentService.getByNoticePeriod(notice));
    }

    @Operation(summary = "Filter Students by Preferred Location")
    @GetMapping("/location/{location}")
    public ResponseEntity<List<StudentDTO>> getByLocation(@PathVariable PreferredLocation location) {
        return ResponseEntity.ok(studentService.getByPreferredLocation(location));
    }
}
