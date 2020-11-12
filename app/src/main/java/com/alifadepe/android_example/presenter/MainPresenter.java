package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.contract.MainContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private SharedPreferencesUtil sharedPreferencesUtil;

    public MainPresenter(MainContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void checkIsUserLogin() {
        if(sharedPreferencesUtil.getToken() != null){
            view.whenUserLogin();
        }
        else {
            view.whenUserNotLogin();
        }
    }
}
