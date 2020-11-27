package com.antriansehat.application.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DaftarAntrian {
    private int id;
    private String residence_number;
    private String status;
    @SerializedName("schedule_id") private int scheduleId;
    @SerializedName("polyclinic_id") private int polyclinicId;
    @SerializedName("health_agency_id") private int healthAgencyId;
    private Date registered_date;

    public String getRegistered_date() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, new Locale("id", "ID"));
        return dateFormat.format(registered_date);
    }

    public DaftarAntrian(int id, String residence_number, int scheduleId, int polyclinicId, int healthAgencyId, Date registered_date) {
        this.id = id;
        this.residence_number = residence_number;
        this.scheduleId = scheduleId;
        this.polyclinicId = polyclinicId;
        this.healthAgencyId = healthAgencyId;
        this.registered_date = registered_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResidence_number() {
        return residence_number;
    }

    public void setResidence_number(String residence_number) {
        this.residence_number = residence_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getPolyclinicId() {
        return polyclinicId;
    }

    public void setPolyclinicId(int polyclinicId) {
        this.polyclinicId = polyclinicId;
    }

    public int getHealthAgencyId() {
        return healthAgencyId;
    }

    public void setHealthAgencyId(int healthAgencyId) {
        this.healthAgencyId = healthAgencyId;
    }

    public void setRegistered_date(Date registered_date) {
        this.registered_date = registered_date;
    }
}
