package com.antriansehat.application.interactor;

import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.model.ScheduleOfHA;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class DaftarAntrianInteractor implements DaftarAntrianContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public DaftarAntrianInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }


    @Override
    public void requestSchedule(ScheduleOfHA scheduleOfHA) {

    }

    @Override
    public void requestRegister(String schedule, String residence_number, String health_agency, String polyclinic, RequestCallback<RegisterResponse> requestCallback) {

    }

    @Override
    public void getResidenceNumber(String id) {

    }

}
