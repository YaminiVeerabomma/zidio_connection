package com.example.entity;

import javax.persistence.*;

import com.example.Enum.Designation;

@Entity
@Table(name = "recruiters")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;                // Recruiter name
    private String email;               // Recruiter email
    private String phone;               // Recruiter phone

    private String companyName;         // Company name
    private String companyDescription;  // About the company
    private String companyWebsite;      // Website URL
    private String companyAddress;      // Office location/address
    private String companySize;         // e.g., Small/Medium/Large

    @Enumerated(EnumType.STRING)
    private Designation designation;    // Recruiter's designation (HR, Manager, etc.)

    // Relation with User (same as Student -> User mapping)
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // Default constructor
    public Recruiter() {}

    // Full constructor
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
