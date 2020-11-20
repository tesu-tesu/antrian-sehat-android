package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListHealthAgencyResponse;
import com.antriansehat.application.api_response.ListPolyclinicResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ListPolyclinicInteractor implements ListPolyclinicContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListPolyclinicInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListPolyclinic(final RequestCallback<List<Polyclinic>> requestCallback) {
//        AndroidNetworking.get(ApiConstant.BASE_URL + "admin/health-agency")
//                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
//                .build()
//                .getAsObject(ListHealthAgencyResponse.class, new ParsedRequestListener<ListPolyclinicResponse>() {
//                    @Override
//                    public void onResponse(ListPolyclinicResponse response) {
//                        if(response != null){
//                            Log.d("ERROR", "NULL");
//                            requestCallback.requestFailed("Null Response");
//                        }
//                        else if(response.success){
//                            Log.d("Success", String.valueOf(response.data));
//                            requestCallback.requestSuccess(response.data);
//                        }
//                        else {
//                            Log.d("ERROR", response.message);
//                            requestCallback.requestFailed(response.message);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.d("ON ERROR", String.valueOf(anError));
//                    }
//                });
    }
}
