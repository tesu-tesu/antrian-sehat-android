package com.antriansehat.application.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String imagePath;
    private String profile_img;
    private String totalWaitingList;
    private String residence_number;

    public User(String id, String name, String email, String phone, String password, String role, String imagePath, String profile_img, String totalWaitingList, String residence_number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.imagePath = imagePath;
        this.profile_img = profile_img;
        this.totalWaitingList = totalWaitingList;
        this.residence_number = residence_number;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public String getTotalWaitingList() {
        return totalWaitingList;
    }

    public String getResidence_number() {
        return residence_number;
    }

    public String getImagePath() {
        return imagePath;
    }
}
