package com.example.DTO;

import java.util.List;

import com.example.Enum.ExperienceLevel;

public class StudentDTO {
	
	public Long id;
	public String name;
	public String email;
	public String phone;
	public String qualification;
	public String resumeURL;
	public List<String> skills;
	public String githubURL;
	public String linkdenURL;
	public ExperienceLevel experienceLevel;
	
	public StudentDTO(Long id,String name,String email,String phone,String qualification,String resumeURL,List<String> skills, String githubURL, String linkdenURL,    ExperienceLevel experienceLevel) {
	this.id=id;
	this.name=name;
	this.email=email;
	this.phone=phone;
	this.qualification=qualification;
	this.resumeURL=resumeURL;
	this.skills = skills;
	this.githubURL = githubURL;
	this.linkdenURL = linkdenURL;
	this. experienceLevel=experienceLevel;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getResumeURL() {
		return resumeURL;
	}

	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
		
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getGithubURL() {
		return githubURL;
	}

	public void setGithubURL(String githubURL) {
		this.githubURL = githubURL;
	}

	public String getLinkdenURL() {
		return linkdenURL;
	}

	public void setLinkdenURL(String linkdenURL) {
		this.linkdenURL = linkdenURL;
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(ExperienceLevel experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	
	
	
	
}
