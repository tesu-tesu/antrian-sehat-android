package com.antriansehat.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListHealthAgencyFromPolyResponse;
import com.antriansehat.application.api_response.ListHealthAgencyResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Pagination;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ListHealthAgencyInteractor implements ListHealthAgencyContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListHealthAgencyInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListHealthAgency(final RequestCallback<Pagination<HealthAgency>> requestCallback, int page) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "health-agency?page=" + page)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListHealthAgencyResponse.class, new ParsedRequestListener<ListHealthAgencyResponse>() {
                    @Override
                    public void onResponse(ListHealthAgencyResponse response) {
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
    public void requestListHealthAgencyOfPolyId(final RequestCallback<List<HealthAgency>> requestCallback, String poly_id) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "health-agency/of-poly/"+Integer.parseInt(poly_id))
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListHealthAgencyFromPolyResponse.class, new ParsedRequestListener<ListHealthAgencyFromPolyResponse>() {
                    @Override
                    public void onResponse(ListHealthAgencyFromPolyResponse response) {
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
