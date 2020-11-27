package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListPolyclinicResponse;
import com.antriansehat.application.api_response.ListPolyclinicResponseFromHA;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.model.Pagination;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ListPolyclinicInteractor implements ListPolyclinicContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListPolyclinicInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListPolyclinic(final RequestCallback<Pagination<Polyclinic>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "admin/poly-master")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListPolyclinicResponse.class, new ParsedRequestListener<ListPolyclinicResponse>() {
                    @Override
                    public void onResponse(ListPolyclinicResponse response) {
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

    @Override
    public void requestListPolyclinic(final RequestCallback<List<PolymasterFromSelectedHA>> requestCallback, String idHA) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "admin/health-agency/"+Integer.parseInt(idHA)+"/polyclinic")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListPolyclinicResponseFromHA.class, new ParsedRequestListener<ListPolyclinicResponseFromHA>() {
                    @Override
                    public void onResponse(ListPolyclinicResponseFromHA response) {
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
                        Log.d("responnya: ", anError.getMessage());
                        requestCallback.requestFailed(anError.getErrorDetail());
                    }
                });
    }
}
