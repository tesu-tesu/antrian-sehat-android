package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.UserDataResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.model.User;
import com.antriansehat.application.util.SharedPreferencesUtil;

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
