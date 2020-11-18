package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.LoginResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.RegisterContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class RegisterInteractor implements RegisterContract.Interactor{
    private SharedPreferencesUtil sharedPreferencesUtil;

    public RegisterInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestRegister(String name, String phone, String email, String password,
                                String confirmPassword,
                                final RequestCallback<RegisterResponse> requestCallback) {
Log.d("ROUTE", ApiConstant.BASE_URL + "auth/register");
        AndroidNetworking.post(ApiConstant.BASE_URL + "auth/register")
                .addBodyParameter("name", name)
                .addBodyParameter("phone", phone)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .addBodyParameter("password_confirmation", confirmPassword)
                .addBodyParameter("role", "Pasien")
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {
                    @Override
                    public void onResponse(RegisterResponse response) {
                        Log.d("RESPONSE", "AA");
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else if(response.is_success){
                            requestCallback.requestSuccess(response);
                        }
                        else {
                            requestCallback.requestFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR", String.valueOf(anError));
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });

    }

    @Override
    public void saveToken(String token) {
        Log.d("token", token);
        sharedPreferencesUtil.setToken(token);
    }
}
