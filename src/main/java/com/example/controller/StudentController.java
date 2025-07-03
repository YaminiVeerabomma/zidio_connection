
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.StudentDTO;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController { 

    @Autowired
    private StudentService studentService;

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createOrUpdateStudent(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.createOrUpdateStudent(dto));
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
