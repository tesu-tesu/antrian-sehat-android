package com.antriansehat.application.model;

public class HealthAgency {
    private String id;
    private String name, address, image, call_center, email;

    public HealthAgency(String id, String name, String address, String image, String call_center, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.call_center = call_center;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCall_center() {
        return call_center;
    }

    public void setCall_center(String call_center) {
        this.call_center = call_center;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
