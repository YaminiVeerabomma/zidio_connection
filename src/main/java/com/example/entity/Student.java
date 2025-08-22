package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;

@Entity
@Table(name = "students")  // lowercase, plural by convention
public class Student {

    @Id
    private Long id; // 👈 same as User ID (no auto-generation, shared via @MapsId)

    private String name;
    private String email;
    private String phone;
    private String qualification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date graduationYear;

    @ElementCollection
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    private String resumeURL;
    private String githubURL;
    private String linkedinURL;

    @Enumerated(EnumType.STRING)
    private PreferredLocation preferredLocation;

    private Double expectedSalary;

    @Enumerated(EnumType.STRING)
    private NoticePeriod noticePeriod;

    @OneToOne
    @MapsId   // 👈 Student uses same PK as User
    @JoinColumn(name = "id")
    private User user;

    // Default constructor
    public Student() {}

    // Constructor with fields
    public Student(String name, String email, String phone, String qualification,
                   String resumeURL, List<String> skills, String githubURL, String linkedinURL,
                   ExperienceLevel experienceLevel, Gender gender, Date graduationYear,
                   PreferredLocation preferredLocation, Double expectedSalary, NoticePeriod noticePeriod, User user) {
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

    public Date getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Date graduationYear) { this.graduationYear = graduationYear; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

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
}
