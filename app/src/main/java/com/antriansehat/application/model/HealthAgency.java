package com.antriansehat.application.model;

import android.media.Image;

public class HealthAgency {
    private String id;
    private String name;
    private String address;
    private Image image;

    public HealthAgency(String id, String name, String address, Image image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Image getImage() {
        return image;
    }
}
