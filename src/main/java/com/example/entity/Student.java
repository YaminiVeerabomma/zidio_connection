package com.example.entity;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredJobLocations;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Long id;  // same as User ID (@MapsId)

    private String name;
    private String email;
    private String phone;
    private String qualification;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer graduationYear;

    @ElementCollection
    @CollectionTable(name = "student_skills", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill")
    private List<String> skills;


    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;
    @Column(name = "resume_url")
    private String resumeURL;
    @Column(name = "github_url")
    private String githubURL;

    @Column(name = "linkedin_url")
    private String linkedinURL;



@ElementCollection(targetClass = PreferredJobLocations.class)
@CollectionTable(name = "student_preferred_locations", joinColumns = @JoinColumn(name = "student_id"))
@Column(name = "preferred_job_location")
@Enumerated(EnumType.STRING)
    private List<PreferredJobLocations> preferredJobLocations;

    private Double expectedSalary;

    @Enumerated(EnumType.STRING)
    private NoticePeriod noticePeriod;

    // Projects field (assuming CSV string)
    @Column(length = 1000)
    private String projects;

    // Relation to User (for @MapsId)
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // ------------------- Constructors -------------------

    public Student() {
        // JPA requires no-arg constructor
    }
    public Student(Long id, String name, String email, String phone, String qualification,
            String resumeURL, List<String> skills, String githubURL, String linkedinURL,
            ExperienceLevel experienceLevel, Gender gender, Integer graduationYear,
            List<PreferredJobLocations> preferredJobLocations, Double expectedSalary,
            NoticePeriod noticePeriod, String projects,User user) {

this.id = id;
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
this.preferredJobLocations = preferredJobLocations;
this.expectedSalary = expectedSalary;
this.noticePeriod = noticePeriod;
this.projects = projects;
this.user = user;
}


// ------------------- Getters & Setters -------------------
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }

public String getQualification() { return qualification; }
public void setQualification(String qualification) { this.qualification = qualification; }

public String getResumeURL() { return resumeURL; }
public void setResumeURL(String resumeURL) { this.resumeURL = resumeURL; }

public List<String> getSkills() { return skills; }
public void setSkills(List<String> skills) { this.skills = skills; }

public String getGithubURL() { return githubURL; }
public void setGithubURL(String githubURL) { this.githubURL = githubURL; }

public String getLinkedinURL() { return linkedinURL; }
public void setLinkedinURL(String linkedinURL) { this.linkedinURL = linkedinURL; }

public ExperienceLevel getExperienceLevel() { return experienceLevel; }
public void setExperienceLevel(ExperienceLevel experienceLevel) { this.experienceLevel = experienceLevel; }

public Gender getGender() { return gender; }
public void setGender(Gender gender) { this.gender = gender; }

public Integer getGraduationYear() { return graduationYear; }
public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

public List<PreferredJobLocations> getPreferredJobLocations() { return preferredJobLocations; }
public void setPreferredJobLocations(List<PreferredJobLocations> preferredJobLocations) { this.preferredJobLocations = preferredJobLocations; }

public Double getExpectedSalary() { return expectedSalary; }
public void setExpectedSalary(Double expectedSalary) { this.expectedSalary = expectedSalary; }

public NoticePeriod getNoticePeriod() { return noticePeriod; }
public void setNoticePeriod(NoticePeriod noticePeriod) { this.noticePeriod = noticePeriod; }

public String getProjects() { return projects; }
public void setProjects(String projects) { this.projects = projects; }
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}


}
