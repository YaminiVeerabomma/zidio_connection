package com.example.service;

import com.example.DTO.ApplicationDTO;
import com.example.DTO.ApplicationRequestDTO;
import com.example.Enum.Status;
import com.example.entity.Application;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

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
    	 if (request.getStudentId() == null || request.getJobId() == null) {
             throw new IllegalArgumentException("StudentId and JobId are required to apply");
         }
        Application application = new Application();
        application.setStudentId(request.getStudentId());
        application.setJobId(request.getJobId());
        application.setResumeURL(request.getResumeURL());
        application.setStatus(Status.APPLIED);
        application.setAppliedDate(new Date());

        return convertToDTO(applicationRepository.save(application));
    }

    // Get all applications
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get by ID
    public Optional<ApplicationDTO> getApplicationById(Long id) {
    	 Optional<Application> apps = applicationRepository.findById(id);
         if (apps.isEmpty()) {
             throw new ResourceNotFoundException("No applications found for studentId: " + id);
         }
        return applicationRepository.findById(id).map(this::convertToDTO);
    }

    // Get by Student
    public List<ApplicationDTO> getApplicationsByStudent(Long studentId) {
    	 List<Application> apps = applicationRepository.findByStudentId(studentId);
         if (apps.isEmpty()) {
             throw new ResourceNotFoundException("No applications found for studentId: " + studentId);
         }
        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get by Job
    public List<ApplicationDTO> getApplicationsByJob(Long jobId) {
    	List<Application> apps = applicationRepository.findByJobId(jobId);
        if (apps.isEmpty()) {
            throw new ResourceNotFoundException("No applications found for jobId: " + jobId);
        }
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update
    public ApplicationDTO updateApplication(Long id, ApplicationDTO dto) {
    	Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
        Application application = new Application();
        application.setId(id);
        application.setStudentId(dto.getStudentId());
        application.setJobId(dto.getJobId());
        application.setResumeURL(dto.getResumeURL());
        application.setStatus(dto.getStatus());
        application.setAppliedDate(dto.getAppliedDate());

        return convertToDTO(applicationRepository.save(application));
    }

    // Delete
    public void deleteApplication(Long id) {
    	 if (!applicationRepository.existsById(id)) {
             throw new ResourceNotFoundException("Application not found with id: " + id);
         }
        applicationRepository.deleteById(id);
    }
}
