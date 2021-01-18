package com.antriansehat.application.interactor;

import android.util.Log;

import com.antriansehat.application.api_response.LoginResponse;
import com.antriansehat.application.api_response.ValidationResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.LoginContract;
import com.antriansehat.application.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginInteractor implements LoginContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    private Gson gson;

    public LoginInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    public void requestLogin(String username, String password, final RequestCallback<LoginResponse> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "auth/login")
            .addBodyParameter("email", username)
            .addBodyParameter("password", password)
            .build()
            .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                @Override
                public void onResponse(LoginResponse response) {
                    if(response == null){
                        requestCallback.requestFailed("Null Response");
                    }
                    else if(response.success){
                        if(response.user.getRole().equals("Pasien"))
                            requestCallback.requestSuccess(response);
                        else
                            requestCallback.requestFailed("Selain user pasien tidak bisa login !");
                    }
                    else {
                        requestCallback.requestFailed(response.message);
                    }
                }

                @Override
                public void onError(ANError anError) {
                    ValidationResponse validation = gson.fromJson(anError.getErrorBody(), ValidationResponse.class);
                    try {
                        requestCallback.requestFailed(validation.getData());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferencesUtil.setToken(token);
    }
}

