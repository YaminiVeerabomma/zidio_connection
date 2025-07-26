
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.StudentDTO;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController { 

    @Autowired
    private StudentService studentService;

    @GetMapping(value="/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @GetMapping(value="/id/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createOrUpdateStudent(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.createOrUpdateStudent(dto));
    }
    @DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping(value = "/gender/{gender}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getStudentsByGender(@PathVariable String gender) {
        return ResponseEntity.ok(studentService.getStudentsByGender(gender));
    }
    @GetMapping(value = "/experience/{experienceLevel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getStudentsByExperienceLevel(@PathVariable String experienceLevel) {
        return ResponseEntity.ok(studentService.getStudentsByExperienceLevel(experienceLevel));
    }
    @GetMapping(value="/graduationYear/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getStudentsByGraduationYear(@PathVariable Integer year) {
        return ResponseEntity.ok(studentService.getStudentsByGraduationYear(year));
    }

    @GetMapping(value="/skills/{skill}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getStudentsBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(studentService.getStudentsBySkill(skill));
    }

}

//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.DTO.StudentDTO;
//import com.example.service.StudentService;
//
//@RestController
//@RequestMapping("/api/students")
//public class StudentController {
//	
//	@Autowired
//	private  StudentService studentService;
//	
//	@GetMapping("/email/{email}")
//	public ResponseEntity<StudentDTO>getStudentByEmail(@PathVariable String email){
//		return ResponseEntity.ok(studentService.getStudentByEmail(email));
//		
//	}
//	
//	@GetMapping("/id/{id}")
//	public ResponseEntity<StudentDTO>getStudentById(@PathVariable Long id){
//		return ResponseEntity.ok(studentService.getStudentById(id));
//		
//	}
//	@PostMapping
//	public  ResponseEntity<StudentDTO>createOrUpadateStudent(@RequestBody StudentDTO dto){
//		return ResponseEntity.ok(studentService.createOrUpdateStudent(dto));
//	}
//	
//
//}
