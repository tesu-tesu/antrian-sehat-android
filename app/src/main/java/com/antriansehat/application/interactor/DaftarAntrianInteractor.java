package com.antriansehat.application.interactor;

import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class DaftarAntrianInteractor implements DaftarAntrianContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public DaftarAntrianInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }


    @Override
    public void requestSchedule() {

    }

    @Override
    public void requestRegister(String schedule, String residence_number, String health_agency, String polyclinic, String order_number) {

    }

    @Override
    public void getResidenceNumber(String id) {

    }

}
