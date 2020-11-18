package com.antriansehat.application.interactor;

import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class ProfileInteractor implements ProfileContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
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

    @Override
    public void deleteToken() {
        sharedPreferencesUtil.clear();
    }
}
