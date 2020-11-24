package com.antriansehat.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListPolyclinicResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ListPolyclinicInteractor implements ListPolyclinicContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListPolyclinicInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListPolyclinic(final RequestCallback<PaginationPolyclinic> requestCallback) {
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
}
