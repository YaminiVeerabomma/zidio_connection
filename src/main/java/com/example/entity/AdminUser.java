package com.example.entity;

import javax.persistence.*;

import com.example.Enum.Role;

@Entity
@Table(name = "admin_users")
public class AdminUser {

    @Id
    private Long id; // shared with User, no @GeneratedValue

    private String name;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active = true;
    private boolean blocked = false;

    @OneToOne
    @MapsId  // shares PK with User
    @JoinColumn(name = "id") // FK references User.id
    private User user;

    public AdminUser() {}

    public AdminUser(User user, String name, String email, Role role) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
