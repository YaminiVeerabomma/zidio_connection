package com.example.service;

import com.example.DTO.ApplicationDTO;
import com.example.DTO.ApplicationRequestDTO;
import com.example.Enum.Status;
import com.example.entity.Application;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ApplicationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    // Convert Entity → DTO
    private ApplicationDTO convertToDTO(Application application) {
        return new ApplicationDTO(
                application.getId(),
                application.getStudentId(),
                application.getJobId(),
                application.getResumeURL(),
                application.getStatus(),
                application.getAppliedDate()
        );
    }

    // Apply for a job
    public ApplicationDTO apply(ApplicationRequestDTO request) {
        log.info("📩 Apply request received -> StudentId: {}, JobId: {}", request.getStudentId(), request.getJobId());

        if (request.getStudentId() == null || request.getJobId() == null) {
            log.error("❌ Apply failed -> Missing StudentId or JobId");
            throw new IllegalArgumentException("StudentId and JobId are required to apply");
        }

        Application application = new Application();
        application.setStudentId(request.getStudentId());
        application.setJobId(request.getJobId());
        application.setResumeURL(request.getResumeURL());
        application.setStatus(Status.APPLIED);
        application.setAppliedDate(new Date());

        Application saved = applicationRepository.save(application);
        log.info("✅ Application created successfully -> ID: {}", saved.getId());

        return convertToDTO(saved);
    }

    // Get all applications
    public List<ApplicationDTO> getAllApplications() {
        log.info("📋 Fetching all applications...");
        List<ApplicationDTO> list = applicationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        log.info("✅ Found {} applications", list.size());
        return list;
    }

    // Get by ID
    public Optional<ApplicationDTO> getApplicationById(Long id) {
        log.info("🔎 Fetch application by ID: {}", id);

        Optional<Application> apps = applicationRepository.findById(id);
        if (apps.isEmpty()) {
            log.warn("❌ No application found with ID: {}", id);
            throw new ResourceNotFoundException("No applications found for id: " + id);
        }

        log.info("✅ Application found -> ID: {}", id);
        return apps.map(this::convertToDTO);
    }

    // Get by Student
    public List<ApplicationDTO> getApplicationsByStudent(Long studentId) {
        log.info("🔎 Fetch applications by StudentId: {}", studentId);

        List<Application> apps = applicationRepository.findByStudentId(studentId);
        if (apps.isEmpty()) {
            log.warn("❌ No applications found for StudentId: {}", studentId);
            throw new ResourceNotFoundException("No applications found for studentId: " + studentId);
        }

        log.info("✅ Found {} applications for StudentId: {}", apps.size(), studentId);
        return apps.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get by Job
    public List<ApplicationDTO> getApplicationsByJob(Long jobId) {
        log.info("🔎 Fetch applications by JobId: {}", jobId);

        List<Application> apps = applicationRepository.findByJobId(jobId);
        if (apps.isEmpty()) {
            log.warn("❌ No applications found for JobId: {}", jobId);
            throw new ResourceNotFoundException("No applications found for jobId: " + jobId);
        }

        log.info("✅ Found {} applications for JobId: {}", apps.size(), jobId);
        return apps.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update
    public ApplicationDTO updateApplication(Long id, ApplicationDTO dto) {
        log.info("✏️ Update application request -> ID: {}", id);

        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("❌ Cannot update -> Application not found with ID: {}", id);
                    return new ResourceNotFoundException("Application not found with id: " + id);
                });

        Application application = new Application();
        application.setId(id);
        application.setStudentId(dto.getStudentId());
        application.setJobId(dto.getJobId());
        application.setResumeURL(dto.getResumeURL());
        application.setStatus(dto.getStatus());
        application.setAppliedDate(dto.getAppliedDate());

        Application updated = applicationRepository.save(application);
        log.info("✅ Application updated successfully -> ID: {}", updated.getId());

        return convertToDTO(updated);
    }

    // Delete
    public void deleteApplication(Long id) {
        log.info("🗑️ Delete application request -> ID: {}", id);

        if (!applicationRepository.existsById(id)) {
            log.warn("❌ Cannot delete -> Application not found with ID: {}", id);
            throw new ResourceNotFoundException("Application not found with id: " + id);
        }

        applicationRepository.deleteById(id);
        log.info("✅ Application deleted -> ID: {}", id);
    }
}
