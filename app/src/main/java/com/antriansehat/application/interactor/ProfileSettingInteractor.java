package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.antriansehat.application.api_response.BaseResponse;
import com.antriansehat.application.api_response.UserDataResponse;
import com.antriansehat.application.api_response.ValidationResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.model.User;
import com.antriansehat.application.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.Executors;

public class ProfileSettingInteractor implements ProfileSettingContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    private Gson gson;

    public ProfileSettingInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

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
                        Log.d("TAG", "onError: " + anError.getErrorBody());
                        ValidationResponse validation = gson.fromJson(anError.getErrorBody(), ValidationResponse.class);
                        try {
                            requestCallback.requestFailed(validation.getData());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void updateProfileImage(String id, File imageFile, final RequestCallback<String> requestCallback) {
        AndroidNetworking.upload(ApiConstant.BASE_URL + "user/change-image/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addMultipartFile("image", imageFile)
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        System.out.println(bytesUploaded/totalBytes + " uploaded...");
                        // do anything with progress
                    }
                })
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse<String>>() {
                    @Override
                    public void onResponse(BaseResponse<String> response) {
                        if(response.success) {
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
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/current")
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
