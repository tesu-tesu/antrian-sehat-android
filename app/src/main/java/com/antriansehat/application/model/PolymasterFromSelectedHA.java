package com.antriansehat.application.model;

import com.google.gson.annotations.SerializedName;

public class PolymasterFromSelectedHA {
    private int id;
    @SerializedName("poly_master_id") private int polyMasterId;
    @SerializedName("health_agency_id") private int healthAgencyId;
    @SerializedName("poly_master") private Polyclinic polyMaster;

    public PolymasterFromSelectedHA(int id, int polyMasterId, int healthAgencyId, Polyclinic polyMaster) {
        this.id = id;
        this.polyMasterId = polyMasterId;
        this.healthAgencyId = healthAgencyId;
        this.polyMaster = polyMaster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPolyMasterId() {
        return polyMasterId;
    }

    public void setPolyMasterId(int polyMasterId) {
        this.polyMasterId = polyMasterId;
    }

    public int getHealthAgencyId() {
        return healthAgencyId;
    }

    public void setHealthAgencyId(int healthAgencyId) {
        this.healthAgencyId = healthAgencyId;
    }

    public Polyclinic getPolyMaster() {
        return polyMaster;
    }

    public void setPolyMaster(Polyclinic polyMaster) {
        this.polyMaster = polyMaster;
    }
}
