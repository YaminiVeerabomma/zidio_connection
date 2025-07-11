package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileUploadService;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value="/resume",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = fileUploadService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}
