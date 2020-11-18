package com.antriansehat.application.interactor;

import com.antriansehat.application.contract.HomeContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class HomeInteractor implements HomeContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public HomeInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
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
