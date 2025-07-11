package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.AdminUserDTO;
import com.example.Enum.Role;
import com.example.service.AdminUserService;




@RestController
@RequestMapping("/api/admins")
public class AdminController {
	
	
	@Autowired
	private AdminUserService adminUserService;

	
	@GetMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdminUserDTO>>getAllUsers(){
		return ResponseEntity.ok(adminUserService.getAllUsers());
	}
	@GetMapping(value="/role/{role}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdminUserDTO>>getByRole(@PathVariable Role role){
		return ResponseEntity.ok(adminUserService.getUserByRole(role));
	}
	
	@PutMapping(value="/{id}/status",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminUserDTO>updateUserStatus(@RequestParam Long id, @RequestParam boolean active){
		return ResponseEntity.ok(adminUserService.upadateStatus(id, active));
	}
	@PutMapping (value="/{id}/block",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminUserDTO>blockUser(@PathVariable Long id){
		AdminUserDTO dto=adminUserService.blockUser(id);
		if(dto==null) {
		return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(value="/{id}/unBlock",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminUserDTO>unBlockUser(@PathVariable Long id){
		AdminUserDTO dto=adminUserService.blockUser(id);
		if(dto==null) {
		return ResponseEntity.notFound().build();
	    }
		return  ResponseEntity.ok(dto);
	
     }
}
