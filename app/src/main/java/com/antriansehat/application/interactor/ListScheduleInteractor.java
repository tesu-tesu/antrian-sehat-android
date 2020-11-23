package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListScheduleResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListScheduleContract;
import com.antriansehat.application.model.Schedule;
import com.antriansehat.application.model.ScheduleOfHA;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ListScheduleInteractor implements ListScheduleContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListScheduleInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListScheduleOfHA(final RequestCallback<List<ScheduleOfHA>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "admin/health-agency/6/polyclinic")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListScheduleResponse.class, new ParsedRequestListener<ListScheduleResponse>() {
                    @Override
                    public void onResponse(ListScheduleResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else if(response.success){
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
