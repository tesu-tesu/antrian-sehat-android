package com.antriansehat.application.model;

public class WaitingListFromSchedule {
    private String day;
    private String polyclinic;
    private String health_agency;
    private String current_number;
    private String latest_number;

    public WaitingListFromSchedule(String day, String polyclinic, String health_agency, String current_number, String latest_number) {
        this.day = day;
        this.polyclinic = polyclinic;
        this.health_agency = health_agency;
        this.current_number = current_number;
        this.latest_number = latest_number;
    }

    public String getDay() {
        return day;
    }

    public String getPolyclinic() {
        return polyclinic;
    }

    public String getHealth_agency() {
        return health_agency;
    }

    public String getCurrent_number() {
        return current_number;
    }

    public String getLatest_number() {
        return latest_number;
    }
}
