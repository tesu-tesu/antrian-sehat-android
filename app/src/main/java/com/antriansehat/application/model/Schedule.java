package com.antriansehat.application.model;

import android.text.format.Time;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Schedule {
    private String id,polyclinic_id, day,time_open,time_close, charOfDay;
    private boolean isChoice = false;
    private Date date;

    public Schedule(String id, String polyclinic_id, String day, String time_open, String time_close, String charOfDay, Date date) {
        this.id = id;
        this.polyclinic_id = polyclinic_id;
        this.day = day;
        this.time_open = time_open;
        this.time_close = time_close;
        this.charOfDay = charOfDay;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolyclinic_id() {
        return polyclinic_id;
    }

    public void setPolyclinic_id(String polyclinic_id) {
        this.polyclinic_id = polyclinic_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime_open() {
        return time_open;
    }

    public void setTime_open(String time_open) {
        this.time_open = time_open;
    }

    public String getTime_close() {
        return time_close;
    }

    public void setTime_close(String time_close) {
        this.time_close = time_close;
    }

    public String getCharOfDay() {
        return charOfDay;
    }

    public void setCharOfDay(String charOfDay) {
        this.charOfDay = charOfDay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getDateOfDay(){
        return String.valueOf(date.getDate());
    }
}
