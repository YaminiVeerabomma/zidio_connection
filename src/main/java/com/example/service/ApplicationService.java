package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.ApplicationDTO;
import com.example.Enum.Status;
import com.example.entity.Application;
import com.example.repository.ApplicationRepository;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepository;

	public ApplicationDTO apply(ApplicationDTO dto) {
		Application app = new Application();
		app.setId(dto.getId());
		app.setStudentId(dto.getStudentId());
		app.setJobId(dto.getJobId());
		app.setResumeURL(dto.getResumeURL());
		app.setStatus(dto.getStatus());
		app.setAppliedDate(dto.getAppliedDate());

		Application saved = applicationRepository.save(app);

		return new ApplicationDTO(
			saved.getId(),
			saved.getStudentId(),
			saved.getJobId(),
			saved.getResumeURL(),
			saved.getStatus(),
			saved.getAppliedDate()
		);
	}

	public List<ApplicationDTO> getApplicationByStudentId(Long studentId) {
		return applicationRepository.findByStudentId(studentId)
			.stream()
			.map(app -> new ApplicationDTO(
				app.getId(),
				app.getStudentId(),
				app.getJobId(),
				app.getResumeURL(),
				app.getStatus(),
				app.getAppliedDate()
			))
			.collect(Collectors.toList());
	}

	public List<ApplicationDTO> getApplicationByJobId(Long jobId) {
		return applicationRepository.findByJobId(jobId)
			.stream()
			.map(app -> new ApplicationDTO(
				app.getId(),
				app.getStudentId(),
				app.getJobId(),
				app.getResumeURL(),
				app.getStatus(),
				app.getAppliedDate()
			))
			.collect(Collectors.toList());
	}

	public void updateStatus(Long id, Status status) {
		Application app = applicationRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Application not found"));
		app.setStatus(status);
		applicationRepository.save(app);
	}
}

