package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Long id;   // ✅ same as User ID

    private String name;
    private String email;
    private String phone;
    private String qualification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

  
    private Integer graduationYear;
    @Column(columnDefinition = "TEXT")
  
    private String skills;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Column(name = "github_url")
    private String githubURL;

    @Column(name = "linkedin_url")
    private String linkedinURL;

    @Column(name = "resume_url")
    private String resumeURL;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private PreferredLocation preferredLocation;

    private Double expectedSalary;

    @Enumerated(EnumType.STRING)
    private NoticePeriod noticePeriod;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")   // ✅ no PrimaryKeyJoinColumn
    private User user;
    @Column(columnDefinition = "TEXT")
    private String projects; 

    public Student() {}

    public Student(String name, String email, String phone, String qualification,String skills,
                   String resumeURL , String githubURL, String linkedinURL,
                   ExperienceLevel experienceLevel, Gender gender, Integer graduationYear,
                   PreferredLocation preferredLocation, Double expectedSalary,
                   NoticePeriod noticePeriod, User user,String projects) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.resumeURL = resumeURL;
    
        this.skills = skills;
        this.githubURL = githubURL;
        this.linkedinURL = linkedinURL;
        this.experienceLevel = experienceLevel;
        this.gender = gender;
        this.graduationYear = graduationYear;
        
        this.preferredLocation = preferredLocation;
        this.expectedSalary = expectedSalary;
        this.noticePeriod = noticePeriod;
        this.user = user;
        this.projects=projects;
    }

  

	

	// Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }
  
    public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public ExperienceLevel getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(ExperienceLevel experienceLevel) { this.experienceLevel = experienceLevel; }
    public String getResumeURL() { return resumeURL; }
    public void setResumeURL(String resumeURL) { this.resumeURL = resumeURL; }
    public String getGithubURL() { return githubURL; }
    public void setGithubURL(String githubURL) { this.githubURL = githubURL; }
    public String getLinkedinURL() { return linkedinURL; }
    public void setLinkedinURL(String linkedinURL) { this.linkedinURL = linkedinURL; }
    public PreferredLocation getPreferredLocation() { return preferredLocation; }
    public void setPreferredLocation(PreferredLocation preferredLocation) { this.preferredLocation = preferredLocation; }
    public Double getExpectedSalary() { return expectedSalary; }
    public void setExpectedSalary(Double expectedSalary) { this.expectedSalary = expectedSalary; }
    public NoticePeriod getNoticePeriod() { return noticePeriod; }
    public void setNoticePeriod(NoticePeriod noticePeriod) { this.noticePeriod = noticePeriod; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}
    
}
