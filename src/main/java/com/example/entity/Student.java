package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String qualification;
	@Enumerated(EnumType.STRING)
    private Gender gender;
    private Date graduationYear;
    @ElementCollection
    private List<String> skills;
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;
    private  String resumeURL;
    private String githubURL;
    private String linkdenURL;
    @ElementCollection
    private List<String> preferredJobLocations;

    private Double expectedSalary;

    @Enumerated(EnumType.STRING)
    private NoticePeriod noticePeriod;

	
	public Student() {}
	
	public Student(Long id, String name, String email, String phone,
            String qualification, String resumeURL, List<String> skills,
            String githubURL, String linkdenURL,
            ExperienceLevel experienceLevel, Gender gender, Date graduationYear,
            List<String> preferredJobLocations, Double expectedSalary, NoticePeriod noticePeriod)
 {
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.qualification=qualification;
		this.resumeURL=resumeURL;
		this.skills = skills;
		this.githubURL = githubURL;
		this.linkdenURL = linkdenURL;
		this.graduationYear= graduationYear;
		this.gender=gender;
		this.experienceLevel= experienceLevel;
		this.preferredJobLocations = preferredJobLocations;
		this.expectedSalary = expectedSalary;
		this.noticePeriod = noticePeriod;

		
		
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(Date graduationYear) {
		this.graduationYear = graduationYear;
		
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(ExperienceLevel experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	public List<String> getPreferredJobLocations() {
		return preferredJobLocations;
	}
	public void setPreferredJobLocations(List<String> preferredJobLocations) {
		this.preferredJobLocations = preferredJobLocations;
	}
	public Double getExpectedSalary() {
		return expectedSalary;
	}
	public void setExpectedSalary(Double expectedSalary) {
		this.expectedSalary = expectedSalary;
	}
	public NoticePeriod getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(NoticePeriod noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	


	
	
	
}
