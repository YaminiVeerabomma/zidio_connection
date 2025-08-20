package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.StudentDTO;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;
import com.example.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
@RestController
@RequestMapping("/api/students")
@Tag(name = "Student API", description = "Operations related to Student management")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value="/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Student by Email", description = "Fetch a student by their email")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        StudentDTO student = studentService.getStudentByEmail(email);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Student by ID", description = "Fetch a student by their unique ID")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Student found"),
    	    @ApiResponse(responseCode = "404", description = "Student not found")
    	})
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @Operation(
//    	    summary = "Create or Update Student",
//    	    description = "Add a new student or update an existing student",
//    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
//    	        description = "Student details",
//    	        required = true,
//    	        content = @Content(
//    	            mediaType = "application/json",
//    	            examples = @ExampleObject(
//    	                value = "{\n" +
//    	                        "  \"name\": \"John Doe\",\n" +
//    	                        "  \"email\": \"john.doe@example.com\",\n" +
//    	                        "  \"phone\": \"9876543210\",\n" +
//    	                        "  \"qualification\": \"B.Tech\",\n" +
//    	                        "  \"gender\": \"MALE\",\n" +
//    	                        "  \"graduationYear\": 2025,\n" +
//    	                        "  \"skills\": [\"Java\", \"Spring Boot\", \"React\"],\n" +
//    	                        "  \"experienceLevel\": \"FRESHER\",\n" +
//    	                        "  \"resumeURL\": \"http://example.com/resume.pdf\",\n" +
//    	                        "  \"githubURL\": \"http://github.com/johndoe\",\n" +
//    	                        "  \"linkedinURL\": \"http://linkedin.com/in/johndoe\",\n" +
//    	                        "  \"preferredJobLocations\": [\"BANGALORE\", \"HYDERABAD\"],\n" +
//    	                        "  \"expectedSalary\": 500000,\n" +
//    	                        "  \"noticePeriod\": \"IMMEDIATE\"\n" +
//    	                        "}"
//    	            )
//    	        )
//    	    )
//    	)

    @Operation(summary = "Create or Update Student", description = "Add a new student or update an existing student")
    public ResponseEntity<StudentDTO> createOrUpdateStudent(@RequestBody StudentDTO dto) {
        StudentDTO savedStudent = studentService.createOrUpdateStudent(dto);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete Student", description = "Delete a student by their ID")
    
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get All Students", description = "Retrieve a list of all students")
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping(value="/gender/{gender}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Students by Gender", description = "Fetch students filtered by gender")
    public ResponseEntity<List<StudentDTO>> getStudentsByGender(@PathVariable String gender) {
        return ResponseEntity.ok(studentService.getStudentsByGender(gender));
    }

    @GetMapping(value="/experience/{experienceLevel}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Students by Experience Level", description = "Fetch students filtered by experience level")
    public ResponseEntity<List<StudentDTO>> getStudentsByExperienceLevel(@PathVariable String experienceLevel) {
        return ResponseEntity.ok(studentService.getStudentsByExperienceLevel(experienceLevel));
    }

    @GetMapping(value="/graduation-year/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Students by Graduation Year", description = "Fetch students filtered by graduation year")
    public ResponseEntity<List<StudentDTO>> getStudentsByGraduationYear(@PathVariable Integer year) {
        return ResponseEntity.ok(studentService.getStudentsByGraduationYear(year));
    }

    @GetMapping(value="/skills/{skill}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Students by Skill", description = "Fetch students filtered by skill")
    public ResponseEntity<List<StudentDTO>> getStudentsBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(studentService.getStudentsBySkill(skill));
    }

    @GetMapping(value="/notice-period/{noticePeriod}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Students by Notice Period", description = "Fetch students filtered by notice period")
    public ResponseEntity<List<StudentDTO>> getByNoticePeriod(@PathVariable String noticePeriod) {
        NoticePeriod np = NoticePeriod.valueOf(noticePeriod.toUpperCase());
        return ResponseEntity.ok(studentService.getStudentsByNoticePeriod(np));
    }

    @GetMapping(value="/preferred-location/{preferredLocation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Students by Preferred Location", description = "Fetch students filtered by preferred location")
    public ResponseEntity<List<StudentDTO>> getByPreferredLocation(@PathVariable String preferredLocation) {
        PreferredLocation pl = PreferredLocation.valueOf(preferredLocation.toUpperCase());
        return ResponseEntity.ok(studentService.getStudentsByPreferredLocation(pl));
    }
}
