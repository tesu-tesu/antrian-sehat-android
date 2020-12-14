package com.antriansehat.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.DaftarAntrianResponse;
import com.antriansehat.application.api_response.ResidenceNumberResponse;
import com.antriansehat.application.api_response.WaitingListFromScheduleResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.model.WaitingListFromSchedule;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class DaftarAntrianInteractor implements DaftarAntrianContract.Interactor {
    private final SharedPreferencesUtil sharedPreferencesUtil;

    public DaftarAntrianInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestResidenceNumber(final RequestCallback<String> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/get-residence-number")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ResidenceNumberResponse.class, new ParsedRequestListener<ResidenceNumberResponse>() {
                    @Override
                    public void onResponse(ResidenceNumberResponse response) {
                        if(response.success) {
                            requestCallback.requestSuccess(response.data);
                        } else {
                            requestCallback.requestFailed(null);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void requestWaitingList(String idSchedule, String date, final RequestCallback<WaitingListFromSchedule> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/get-waiting-list/" + idSchedule+ "/" + date)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(WaitingListFromScheduleResponse.class, new ParsedRequestListener<WaitingListFromScheduleResponse>() {
                    @Override
                    public void onResponse(WaitingListFromScheduleResponse response) {
                        if(response.success) {
                            requestCallback.requestSuccess(response.data);
                        } else {
                            requestCallback.requestFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorBody());
                    }
                });
    }

    @Override
    public void requestRegister(String idSchedule, String date, String residenceNumber, final RequestCallback<WaitingList> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "admin/waiting-list/")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("schedule", idSchedule)
                .addBodyParameter("registered_date", String.valueOf(date))
                .addBodyParameter("residence_number", residenceNumber)
                .build()
                .getAsObject(DaftarAntrianResponse.class, new ParsedRequestListener<DaftarAntrianResponse>() {

                    @Override
                    public void onResponse(DaftarAntrianResponse response) {
                        if(response.success){
                            requestCallback.requestSuccess(response.data);
                        }
                        else {
                            requestCallback.requestFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorBody());
                    }
                });
    }


}
