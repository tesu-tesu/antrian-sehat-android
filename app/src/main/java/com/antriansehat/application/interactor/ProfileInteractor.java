package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.adapter.ListBookedResidenceNumberAdapter;
import com.antriansehat.application.api_response.ListBookedResidenceNumberResponse;
import com.antriansehat.application.api_response.UserDataResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.model.User;
import com.antriansehat.application.util.SharedPreferencesUtil;

import java.util.List;

public class ProfileInteractor implements ProfileContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void deleteToken() {
        sharedPreferencesUtil.clear();
    }

    @Override
    public void setDataUser(final RequestCallback<User> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/current")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserDataResponse.class, new ParsedRequestListener<UserDataResponse>() {
                    @Override
                    public void onResponse(UserDataResponse response) {
                        if(response.success){
                            requestCallback.requestSuccess(response.data);
                            Log.d("message", "onResponse: " + response.message);
                        }
                        else {
                            Log.d("get user:", "error");
                            Log.d("LOG", "onResponse: " + response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorBody());
                        Log.d("LOG", "onError: " + anError.getErrorBody());
                    }
                });
    }

    @Override
    public void requestBookedResidenceNumber(final RequestCallback<List<String>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/booked-residence-number")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListBookedResidenceNumberResponse.class, new ParsedRequestListener<ListBookedResidenceNumberResponse>() {
                    @Override
                    public void onResponse(ListBookedResidenceNumberResponse response) {
                        if(response.success)
                            requestCallback.requestSuccess(response.data);
                        else
                            requestCallback.requestFailed(response.message);
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}
