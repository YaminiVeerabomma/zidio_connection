package com.example.DTO;

import com.example.Enum.Designation;

public class RecruiterDTO {
    public Long id;
    public String name;
    public String email;
    public String companyName;
    public String phone;
    public String companydiscription;
    public String companyWebsite;
    public  Designation designation;

    public RecruiterDTO(Long id, String name, String email, String companyName, String phone, String companydiscription, String companyWebsite, Designation designation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.phone = phone;
        this.companydiscription = companydiscription;
        this.companyWebsite = companyWebsite;
        this. designation= designation;
    }
}


