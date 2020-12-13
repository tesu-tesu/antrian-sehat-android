package com.antriansehat.application.interactor;

import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.util.SharedPreferencesUtil;

public class ProfileSettingInteractor implements ProfileSettingContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileSettingInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void updateProfile() {

    }
}
