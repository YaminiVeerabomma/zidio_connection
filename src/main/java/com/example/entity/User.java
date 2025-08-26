package com.example.entity;

import javax.persistence.*;

import com.example.Enum.Role;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Primary key

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // ------------------ ROLE-BASED RELATIONSHIPS ------------------

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Recruiter recruiter;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private AdminUser adminUser;

    // ------------------ CONSTRUCTORS ------------------

    public User() {}

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ------------------ GETTERS & SETTERS ------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            student.setUser(this); // maintain both sides
        }
    }

    public Recruiter getRecruiter() { return recruiter; }
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
        if (recruiter != null) {
            recruiter.setUser(this); // maintain both sides
        }
    }

    public AdminUser getAdminUser() { return adminUser; }
    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
        if (adminUser != null) {
            adminUser.setUser(this); // maintain both sides
        }
    }
}

//package com.example.entity;
//
//
//import javax.persistence.*;
//
//
//import com.example.Enum.Role;
//
//
//import javax.persistence.*;
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;   // ✅ lowercase for consistency
//
//    private String name;
//
//    @Column(unique = true)
//    private String email;
//
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    public User() {}
//
//    public User(String name, String email, String password, Role role) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//
//    public Role getRole() { return role; }
//    public void setRole(Role role) { this.role = role; }
//}
