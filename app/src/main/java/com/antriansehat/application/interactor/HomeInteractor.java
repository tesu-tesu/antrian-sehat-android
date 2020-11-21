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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

//import static androidx.constraintlayout.widget.StateSet.TAG;

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
        AndroidNetworking.get(ApiConstant.BASE_URL + "user/show-nearest-waiting-list")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(NearestWaitingListResponse.class, new ParsedRequestListener<NearestWaitingListResponse>() {
                    //kalau mau getasobject dan langsung parse, atribut di class harus sama kaya atribut di response api
                    @Override
                    public void onResponse(NearestWaitingListResponse response) {
                            if(response.success) {
                                requestCallback.requestSuccess(response.waiting_list);
//                                Log.d("WAITINGLIST", "onResponse: " + response.waiting_list.getStatus());
                            } else {
                                requestCallback.requestFailed("Anda belum memiliki antrian yang akan datang");
                            }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void requestArticle(RequestCallback<List<Article>> requestCallback) {

    }

    @Override
    public void logout() {

    }
}
