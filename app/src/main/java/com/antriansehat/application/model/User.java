package com.antriansehat.application.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String residence_number;

    public User(String id, String name, String email, String password, String residence_number, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.residence_number = residence_number;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
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

    public String getPassword() {
        return password;
    }

    public String getResidenceNumber() {
        return residence_number;
    }
}
