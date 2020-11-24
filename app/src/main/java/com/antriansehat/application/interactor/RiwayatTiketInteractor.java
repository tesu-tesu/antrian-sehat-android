package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.UserWaitingListResponse;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.RiwayatTiketContract;
import com.antriansehat.application.model.UserWaitingList;
import com.antriansehat.application.util.SharedPreferencesUtil;
import com.antriansehat.application.callback.RequestCallback;

public class RiwayatTiketInteractor implements RiwayatTiketContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public RiwayatTiketInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestTicket(final RequestCallback<UserWaitingList> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/get-waiting-list")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserWaitingListResponse.class, new ParsedRequestListener<UserWaitingListResponse>() {
                    @Override
                    public void onResponse(UserWaitingListResponse response) {
                        if(response.success){
                            requestCallback.requestSuccess(response.waitingList);
                        }
                        else {
                            requestCallback.requestFailed("Gagal");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorBody());
                    }
                });
    }
}
