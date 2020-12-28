package com.antriansehat.application.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String profile_image;
    private String totalWaitingList;
    private String residence_number;

    public User(String id, String name, String email, String phone, String password, String role, String profile_image, String totalWaitingList, String residence_number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.profile_image = profile_image;
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

    public String getProfile_image() {
        return profile_image;
    }

    public String getTotalWaitingList() {
        return totalWaitingList;
    }

    public String getResidence_number() {
        return residence_number;
    }
}
