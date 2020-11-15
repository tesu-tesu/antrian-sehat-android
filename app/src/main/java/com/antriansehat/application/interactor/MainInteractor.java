package com.antriansehat.application.interactor;

import com.antriansehat.application.contract.MainContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class MainInteractor implements MainContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public MainInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        if(sharedPreferencesUtil.getToken() != null){
            return true;
        }
        else {
            return false;
        }
    }
}
