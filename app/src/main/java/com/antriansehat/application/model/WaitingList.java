package com.antriansehat.application.model;

import java.util.Date;

public class WaitingList {
    private String id;
    private String status;
    private String barcode;
    private String residenceNumber;
    private Polyclinic polyclinic;
    private HealthAgency healthAgency;
    private Date registeredDate;
    private int orderNumber;
    private int latestWaitingList;
    private int currentWaitingList;


    public WaitingList(String id, String residenceNumber, Polyclinic polyclinic, HealthAgency healthAgency) {
        this.id = id;
        this.residenceNumber = residenceNumber;
        this.polyclinic = polyclinic;
        this.healthAgency = healthAgency;
    }

    public void setOrderInfo(int orderNumber, int currentWaitingList, int latestWaitingList) {
        this.orderNumber = orderNumber;
        this.currentWaitingList = currentWaitingList;
        this.latestWaitingList = latestWaitingList;
    }

    public void setAdditionalInfo(Date registeredDate, String status, String barcode) {
        this.registeredDate = registeredDate;
        this.status = status;
        this.barcode = barcode;
    }

    public WaitingList(String id, String status, String barcode, String residenceNumber,
                       Polyclinic polyclinic, HealthAgency healthAgency, Date registeredDate,
                       int orderNumber, int latestWaitingList, int currentWaitingList) {
        this.id = id;
        this.status = status;
        this.barcode = barcode;
        this.residenceNumber = residenceNumber;
        this.polyclinic = polyclinic;
        this.healthAgency = healthAgency;
        this.registeredDate = registeredDate;
        this.orderNumber = orderNumber;
        this.latestWaitingList = latestWaitingList;
        this.currentWaitingList = currentWaitingList;
    }

    public String getId() {
        return id;
    }

    public Polyclinic getPolyclinic() {
        return polyclinic;
    }

    public HealthAgency getHealthAgency() {
        return healthAgency;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getLatestWaitingList() {
        return latestWaitingList;
    }

    public int getCurrentWaitingList() {
        return currentWaitingList;
    }

    public String getResidenceNumber() {
        return residenceNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getStatus() {
        return status;
    }

}
