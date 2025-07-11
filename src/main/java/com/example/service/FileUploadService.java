package com.example.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    // Directory where files will be stored
    private static final String UPLOAD_DIR = "uploads/";

    public String uploadFile(MultipartFile file) throws IOException {
        // Create uploads directory if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Normalize file name
        String originalFilename = Path.of(file.getOriginalFilename()).getFileName().toString();

        // Define target location
        Path targetPath = Paths.get(UPLOAD_DIR).resolve(originalFilename);

        // Copy file to the target location (replace existing file with same name)
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Return the relative file path (or URL if you have a static resources setup)
        return targetPath.toString();
    }
}
