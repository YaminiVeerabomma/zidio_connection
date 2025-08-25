package com.example.DTO;

import java.util.List;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;

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
    public Gender gender;
    public Integer graduationYear;
    public PreferredLocation preferredLocation;
    public Double expectedSalary;
    public NoticePeriod noticePeriod;
    public String projects;
    // ✅ Default constructor
    public StudentDTO() {}

    // ✅ Full constructor
    public StudentDTO(Long id, String name, String email, String phone, String qualification,
                      String resumeURL, List<String> skills, String githubURL, String linkdenURL,
                      ExperienceLevel experienceLevel, Gender gender, Integer graduationYear,
                      PreferredLocation preferredLocation, Double expectedSalary, NoticePeriod noticePeriod,String projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.resumeURL = resumeURL;
        this.skills = skills;
        this.githubURL = githubURL;
        this.linkdenURL = linkdenURL;
        this.experienceLevel = experienceLevel;
        this.gender = gender;
        this.graduationYear = graduationYear;
        this.preferredLocation = preferredLocation;
        this.expectedSalary = expectedSalary;
        this.noticePeriod = noticePeriod;
        this.projects=projects;
    }
}
