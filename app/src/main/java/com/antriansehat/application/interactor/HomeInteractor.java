package com.antriansehat.application.interactor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.antriansehat.application.api_response.ListBookResponse;
import com.antriansehat.application.api_response.NearestWaitingListResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.HomeContract;
import com.antriansehat.application.model.Article;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.util.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.List;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class HomeInteractor implements HomeContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public HomeInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        return (sharedPreferencesUtil.getToken() != null);
    }

    @Override
    public void requestNearestWaitingList(final RequestCallback<WaitingList> requestCallback) {
        /*AndroidNetworking.get(ApiConstant.BASE_URL + "show-nearest-waiting-list/")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(NearestWaitingListResponse.class, new ParsedRequestListener<NearestWaitingListResponse>() {
                    @Override
                    public void onResponse(NearestWaitingListResponse response) {
                        if(response != null) {
                            requestCallback.requestSuccess(response.waitingList);
                        } else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });*/
        AndroidNetworking.get(ApiConstant.BASE_URL + "show-nearest-waiting-list/")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
//                .getAsJSONObject(NearestWaitingListResponse.class, new ParsedRequestListener<NearestWaitingListResponse>() {
//                    @Override
//                    public void onResponse(NearestWaitingListResponse response) {
//                        if(response != null) {
//                            requestCallback.requestSuccess(response.waitingList);
//                        } else {
//                            requestCallback.requestFailed("Null Response");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallback.requestFailed(anError.getMessage());
//                    }
//                });
    }

    @Override
    public void requestArticle(RequestCallback<List<Article>> requestCallback) {

    }

    @Override
    public void logout() {

    }
}
