package com.example.controller;

import com.example.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api/uploadFile")
@Tag(name = "File Upload API", description = "Endpoints to upload resumes, certificates, and invoices")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value="/resume", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload Resume", description = "Upload a resume file (any format)")
    public ResponseEntity<String> uploadResume(
            @Parameter(description = "Resume file to upload", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file, "resume"));
    }

    @PostMapping(value="/image", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload Certificate", description = "Upload a JPG certificate file")
    public ResponseEntity<String> uploadCertificate(
            @Parameter(description = "Certificate file to upload (JPG only)", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {

        if (!"image/jpeg".equalsIgnoreCase(file.getContentType())) {
            return ResponseEntity.badRequest().body("Only JPG files are allowed.");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".jpg")) {
            return ResponseEntity.badRequest().body("Invalid file type. Filename must end with .jpg");
        }

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                return ResponseEntity.badRequest().body("File is not a valid image.");
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error reading image file.");
        }

        return ResponseEntity.ok(fileUploadService.uploadFile(file, "certificate"));
    }

    @PostMapping(value="/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload Invoice", description = "Upload an invoice file")
    public ResponseEntity<String> uploadInvoice(
            @Parameter(description = "Invoice file to upload", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file, "invoice"));
    }
}
