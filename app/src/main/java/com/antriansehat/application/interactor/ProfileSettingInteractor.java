package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.UserDataResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.model.User;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class ProfileSettingInteractor implements ProfileSettingContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileSettingInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestUpdateProfile(String id, String name, String email, String nik, String phone, final RequestCallback<User> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "user/"+ id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("name", name)
                .addBodyParameter("phone", phone)
                .addBodyParameter("email", email)
                .addBodyParameter("residence_number", nik)
                .addBodyParameter("role", "Pasien")
                .addBodyParameter("_method", "PUT")
                .build()
                .getAsObject(UserDataResponse.class, new ParsedRequestListener<UserDataResponse>() {
                    @Override
                    public void onResponse(UserDataResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        } else if(response.success){
                            requestCallback.requestSuccess(response.data);
                        } else {
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
    public void setDataUser(final RequestCallback<User> requestCallback){
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/get-current-user")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserDataResponse.class, new ParsedRequestListener<UserDataResponse>() {
                    @Override
                    public void onResponse(UserDataResponse response) {
                        if(response.success){
                            requestCallback.requestSuccess(response.data);
                        }
                        else {
                            Log.d("get user:", "error");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorBody());
                    }
                });
    }
}
