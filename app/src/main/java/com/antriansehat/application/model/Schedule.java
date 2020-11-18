package com.antriansehat.application.model;

import android.text.format.Time;

import java.util.Date;

public class Schedule {
    private int id;
    private String day;
    private Date date;
    private Time timeOpen;
    private Time timeClose;

    public Schedule(int id, String day, Time timeOpen, Time timeClose) {
        this.id = id;
        this.day = day;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimeOpen() {
        return timeOpen;
    }

    public Time getTimeClose() {
        return timeClose;
    }
}
