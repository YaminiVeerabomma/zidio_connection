package com.example.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ApplicationDTO;
import com.example.Enum.Status;
import com.example.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicaionService;
	
	
	@PostMapping(value="/apply",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ApplicationDTO>apply(@RequestBody ApplicationDTO dto){
		return ResponseEntity.ok(applicaionService.apply(dto));
	}
	
	@GetMapping(value="/student/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ApplicationDTO>>getApplicationByStudentId(@PathVariable Long studentId){
		return ResponseEntity.ok(applicaionService.getApplicationByStudentId(studentId));
	}
	@GetMapping(value="/job/{jobId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ApplicationDTO>>getApplicationByJoId(@PathVariable Long jobId){
		return ResponseEntity.ok(applicaionService.getApplicationByJobId(jobId));
	}
	@PostMapping(value="/{id}/status",produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateStatus(@PathVariable Long id,@RequestParam Status status) {
		applicaionService.updateStatus(id, status);
	}

}