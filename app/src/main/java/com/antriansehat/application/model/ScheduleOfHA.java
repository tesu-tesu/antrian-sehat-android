package com.antriansehat.application.model;

import java.util.List;

public class ScheduleOfHA {
    private String id, poly_master_id, health_agency_id;
    private List<Schedule> sorted;
    private HealthAgency health_agency;
    private Polyclinic poly_master;

    public ScheduleOfHA(String id, String poly_master_id, String health_agency_id,
                        List<Schedule> sorted, HealthAgency health_agency, Polyclinic poly_master) {
        this.id = id;
        this.poly_master_id = poly_master_id;
        this.health_agency_id = health_agency_id;
        this.sorted = sorted;
        this.health_agency = health_agency;
        this.poly_master = poly_master;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoly_master_id() {
        return poly_master_id;
    }

    public void setPoly_master_id(String poly_master_id) {
        this.poly_master_id = poly_master_id;
    }

    public String getHealth_agency_id() {
        return health_agency_id;
    }

    public void setHealth_agency_id(String health_agency_id) {
        this.health_agency_id = health_agency_id;
    }

    public List<Schedule> getSorted() {
        return sorted;
    }

    public void setSorted(List<Schedule> sorted) {
        this.sorted = sorted;
    }

    public HealthAgency getHealth_agency() {
        return health_agency;
    }

    public void setHealth_agency(HealthAgency health_agency) {
        this.health_agency = health_agency;
    }

    public Polyclinic getPoly_master() {
        return poly_master;
    }

    public void setPoly_master(Polyclinic poly_master) {
        this.poly_master = poly_master;
    }
}
