package com.example.entity;

import javax.persistence.*;

import com.example.Enum.Designation;

@Entity
@Table(name = "recruiters")
public class Recruiter {

    @Id
    private Long id;   // ✅ no auto-generation, shares PK with User

    private String name;
    private String email;
    private String phone;

    private String companyName;
    private String companyDescription;
    private String companyWebsite;
    private String companyAddress;
    private String companySize;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")   // ✅ FK = PK, no extra column
    private User user;

    public Recruiter() {}

    public Recruiter(Long id, String name, String email, String phone,
                     String companyName, String companyDescription,
                     String companyWebsite, String companyAddress, String companySize,
                     Designation designation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.companyWebsite = companyWebsite;
        this.companyAddress = companyAddress;
        this.companySize = companySize;
        this.designation = designation;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getCompanyDescription() { return companyDescription; }
    public void setCompanyDescription(String companyDescription) { this.companyDescription = companyDescription; }
    public String getCompanyWebsite() { return companyWebsite; }
    public void setCompanyWebsite(String companyWebsite) { this.companyWebsite = companyWebsite; }
    public String getCompanyAddress() { return companyAddress; }
    public void setCompanyAddress(String companyAddress) { this.companyAddress = companyAddress; }
    public String getCompanySize() { return companySize; }
    public void setCompanySize(String companySize) { this.companySize = companySize; }
    public Designation getDesignation() { return designation; }
    public void setDesignation(Designation designation) { this.designation = designation; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
