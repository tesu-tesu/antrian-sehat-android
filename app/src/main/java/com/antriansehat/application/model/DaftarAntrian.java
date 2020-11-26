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

}
