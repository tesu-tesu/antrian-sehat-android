package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListBookResponse;
import com.antriansehat.application.api_response.ListHealthAgencyResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ListHealthAgencyInteractor implements ListHealthAgencyContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListHealthAgencyInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListHealthAgency(final RequestCallback<List<HealthAgency>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "admin/health-agency")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListHealthAgencyResponse.class, new ParsedRequestListener<ListHealthAgencyResponse>() {
                    @Override
                    public void onResponse(ListHealthAgencyResponse response) {
                        if(response != null){
                            Log.d("ERROR", "NULL");
                            requestCallback.requestFailed("Null Response");
                        }
                        else if(response.success){
                            Log.d("Success", String.valueOf(response.data));
                            requestCallback.requestSuccess(response.data);
                        }
                        else {
                            Log.d("ERROR", response.message);
                            requestCallback.requestFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ON ERROR", String.valueOf(anError));
                    }
                });
    }
}
