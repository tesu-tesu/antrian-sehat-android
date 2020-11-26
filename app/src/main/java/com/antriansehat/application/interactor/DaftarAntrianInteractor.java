package com.antriansehat.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.DaftarAntrianResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.model.ScheduleOfHA;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class DaftarAntrianInteractor implements DaftarAntrianContract.Interactor {
    private final SharedPreferencesUtil sharedPreferencesUtil;

    public DaftarAntrianInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestSelectedSchedule(ScheduleOfHA scheduleOfHA) {

    }

    @Override
    public void getResidenceNumber(String id) {

    }

    @Override
    public void requestRegister(String residence_number, String polyclinic, String health_agency, final RequestCallback<DaftarAntrianResponse> daftarAntrianResponseRequestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "pasien/book-waiting-list/")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("residence_number", residence_number)
                .addBodyParameter("polyclinic", polyclinic)
                .addBodyParameter("health_agency", health_agency)
                .build()
                .getAsObject(DaftarAntrianResponse.class, new ParsedRequestListener<DaftarAntrianResponse>() {

                    @Override
                    public void onResponse(DaftarAntrianResponse response) {
                        if(response == null){
                            daftarAntrianResponseRequestCallback.requestFailed("Null Response");
                        }
                        else if(response.success){
                            daftarAntrianResponseRequestCallback.requestSuccess(response);
                        }
                        else {
                            daftarAntrianResponseRequestCallback.requestFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        daftarAntrianResponseRequestCallback.requestFailed(anError.getErrorBody());
                    }
                });
    }
}
