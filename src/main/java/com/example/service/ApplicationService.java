//package com.example.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.DTO.ApplicationDTO; 
//import com.example.entity.Application;
//import com.example.entity.Status;
//import com.example.repository.ApplicationRepository;
//
//@Service
//public class ApplicationService {
//
//    @Autowired
//    private ApplicationRepository applicationRepository;
//
//    public ApplicationDTO apply(ApplicationDTO dto) {
//        Application app = new Application();
//        app.setId(dto.id);
//        app.setStudentId(dto.studentId);
//        app.setJobId(dto.jobId);
//        app.setResumeURL(dto.resumeURL);
//        app.setStatus(dto.status);
//        app.setAppliedDate(dto.appliedDate);
//
//        Application saved = applicationRepository.save(app);
//
//        return new ApplicationDTO(
//            saved.getId(),
//            saved.getStudentId(),
//            saved.getJobId(),
//            saved.getResumeURL(),
//            saved.getStatus(),
//            saved.getAppliedDate()
//        );
//    }
//
//    public List<ApplicationDTO> getApplicationByStudentId(Long studentId) {
//        return applicationRepository.findByStudentId(studentId).stream()
//            .map(app -> new ApplicationDTO(
//                app.getId(),
//                app.getStudentId(),
//                app.getJobId(),
//                app.getResumeURL(),
//                app.getStatus(),
//                app.getAppliedDate()
//            ))
//            .collect(Collectors.toList());
//    }
//
//    public List<ApplicationDTO> getApplicationByJobId(Long jobId) {
//        return applicationRepository.findByJobId(jobId).stream()
//            .map(app -> new ApplicationDTO(
//                app.getId(),
//                app.getStudentId(),
//                app.getJobId(),
//                app.getResumeURL(),
//                app.getStatus(),
//                app.getAppliedDate()
//            ))
//            .collect(Collectors.toList());
//    }
//
//    public void updateStatus(Long id, Status status) {
//        Application app = applicationRepository.findById(id).orElseThrow();
//        app.setStatus(status);
//        applicationRepository.save(app);
//    }
//}
