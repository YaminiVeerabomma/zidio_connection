package com.example.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileUploadService;

@RestController
@RequestMapping("/api/uploadFile")
public class FileUploadController {

    @Autowired 
    private FileUploadService fileUploadService;

    @PostMapping(value="/resume", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file, "resume"));
    }

    @PostMapping(value="/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadCertificate(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file, "certificate"));
    }

    @PostMapping(value="/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadInvoice(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file, "invoice"));
    }
}
