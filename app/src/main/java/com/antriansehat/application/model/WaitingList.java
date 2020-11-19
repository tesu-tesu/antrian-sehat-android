package com.antriansehat.application.model;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class WaitingList {
    private String id;
    private String status;
    private String barcode;
    private String residence_number;
    private String polyclinic;
    private String health_agency;
    private Date registered_date;
    private String order_number;
    private String latest_number;
    private String current_number;

    public WaitingList(String id, String status, String barcode, String residence_number, String polyclinic, String health_agency, Date registered_date, String order_number, String latest_number, String current_number) {
        this.id = id;
        this.status = status;
        this.barcode = barcode;
        this.residence_number = residence_number;
        this.polyclinic = polyclinic;
        this.health_agency = health_agency;
        this.registered_date = registered_date;
        this.order_number = order_number;
        this.latest_number = latest_number;
        this.current_number = current_number;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getResidence_number() {
        return residence_number;
    }

    public String getPolyclinic() {
        return polyclinic;
    }

    public String getHealth_agency() {
        return health_agency;
    }

    public String getRegistered_date() {
        Locale locale = new Locale("id", "ID");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);

        return dateFormat.format(registered_date);
    }

    public String getOrder_number() {
        return order_number;
    }

    public String getLatest_number() {
        return latest_number;
    }

    public String getCurrent_number() {
        return current_number;
    }
}
