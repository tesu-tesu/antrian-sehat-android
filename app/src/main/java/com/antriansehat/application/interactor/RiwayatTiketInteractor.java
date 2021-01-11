package com.antriansehat.application.interactor;


import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.BaseResponse;
import com.antriansehat.application.api_response.ListOfWaitingListResponse;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.RiwayatTiketContract;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.util.SharedPreferencesUtil;
import com.antriansehat.application.callback.RequestCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class RiwayatTiketInteractor implements RiwayatTiketContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public RiwayatTiketInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    public void requestTicket(final int ticketType, final RequestCallback<List<WaitingList>> requestCallback) {
        String URL = ApiConstant.BASE_URL + "waiting-list/";
        if(ticketType == RiwayatTiketContract.TODAY_TICKET)
            URL += "today";
        if(ticketType == RiwayatTiketContract.PAST_TICKET)
            URL += "past";
        if(ticketType == RiwayatTiketContract.FUTURE_TICKET)
            URL += "future";

        System.out.println(URL);
        AndroidNetworking.get(URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListOfWaitingListResponse.class, new ParsedRequestListener<ListOfWaitingListResponse>() {

                    @Override
                    public void onResponse(ListOfWaitingListResponse response) {
                        if(response.success){
                            Log.d("RESP", "onResponse: " + response.data);
                            requestCallback.requestSuccess(response.data);
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
