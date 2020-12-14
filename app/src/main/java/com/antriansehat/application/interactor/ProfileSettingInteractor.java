package com.antriansehat.application.interactor;

import com.antriansehat.application.api_response.ProfileSettingResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class ProfileSettingInteractor implements ProfileSettingContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileSettingInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestUpdateProfile(String name, String email, String nik, final RequestCallback<ProfileSettingResponse> requestCallback) {
//        AndroidNetworking.post(ApiConstant.BASE_URL + "auth/register")
//                .addBodyParameter("name", name)
//                .addBodyParameter("phone", phone)
//                .addBodyParameter("email", email)
//                .addBodyParameter("password", password)
//                .addBodyParameter("password_confirmation", confirmPassword)
//                .addBodyParameter("role", "Pasien")
//                .build()
//                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {
//                    @Override
//                    public void onResponse(RegisterResponse response) {
//                        if(response == null){
//                            requestCallback.requestFailed("Null Response");
//                        }
//                        else if(response.success){
//                            requestCallback.requestSuccess(response);
//                        }
//                        else {
//                            requestCallback.requestFailed(response.message);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallback.requestFailed(anError.getErrorBody());
//                    }
//                });
    }
}
