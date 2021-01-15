package com.antriansehat.application.interactor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.api_response.ValidationResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.RegisterContract;
import com.antriansehat.application.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RegisterInteractor implements RegisterContract.Interactor{
    private SharedPreferencesUtil sharedPreferencesUtil;
    private Gson gson;

    public RegisterInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    public void requestRegister(String name, String phone, String email, String password,
                                String confirmPassword,
                                final RequestCallback<RegisterResponse> requestCallback) {

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
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else if(response.success){
                            requestCallback.requestSuccess(response);
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
