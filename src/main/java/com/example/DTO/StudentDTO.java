package com.example.DTO;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;

import com.example.Enum.PreferredJobLocations;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class StudentDTO {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email must be valid")
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;
    @NotBlank(message = "Qualification is required")
    private String qualification;
    private String resumeURL;
    @NotEmpty(message = "Skills cannot be empty")
    private List<String> skills;
    private String githubURL;
    private String linkedinURL;
    private ExperienceLevel experienceLevel;
    private Gender gender;

    @Min(value = 2000, message = "Graduation year must be >= 2000")
    private Integer graduationYear;
    private List<PreferredJobLocations> preferredJobLocations;  // updated enum list

    @Positive(message = "Expected salary must be positive")
    private Double expectedSalary;
    private NoticePeriod noticePeriod;
    private String projects;
    public StudentDTO() {}
    // ------------------- Constructor -------------------
    public StudentDTO(Long id, String name, String email, String phone, String qualification,
                      String resumeURL, List<String> skills, String githubURL, String linkedinURL,
                      ExperienceLevel experienceLevel, Gender gender, Integer graduationYear,
                      List<PreferredJobLocations> preferredJobLocations, Double expectedSalary,
                      NoticePeriod noticePeriod, String projects) {

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
}
