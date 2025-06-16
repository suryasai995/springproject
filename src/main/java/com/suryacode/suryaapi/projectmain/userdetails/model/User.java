package com.suryacode.suryaapi.projectmain.userdetails.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users-details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    private String email;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;
    private Long userid;
    private String gender;
    private Long phone;    
    private String address;   
    private String lang;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User(Long id, String email, String name, Long userid, String gender, Long phone, String address,
            String lang) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userid = userid;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.lang = lang;
    }

    // Constructors
    public User() {}

   public User( String email, String name, Long userid, String gender, Long phone, String address,
            String lang) {
        this.email = email;
        this.name = name;
        this.userid = userid;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.lang = lang;
    }

   
}
