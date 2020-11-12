package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.ListBookResponse;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ListBookContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class ListBookPresenter implements ListBookContract.Presenter {
    private ListBookContract.View view;
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListBookPresenter(ListBookContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListBook() {
        view.startLoading();

        AndroidNetworking.get(ApiConstant.BASE_URL + "books.php")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListBookResponse.class, new ParsedRequestListener<ListBookResponse>() {
                    @Override
                    public void onResponse(ListBookResponse response) {
                        view.endLoading();

                        if(response != null){
                            view.showListBook(response.data);
                        }
                        else {
                            view.showError("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.endLoading();
                        view.showError(anError.getMessage());
                    }
                });
    }

    @Override
    public void logout() {
        sharedPreferencesUtil.clear();
    }
}
