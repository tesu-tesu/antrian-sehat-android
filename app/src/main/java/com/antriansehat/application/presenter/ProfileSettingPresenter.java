package com.antriansehat.application.presenter;

import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.interactor.ProfileSettingInteractor;
import com.antriansehat.application.view.ProfileSettingActivity;

public class ProfileSettingPresenter implements ProfileSettingContract.Presenter {
    private ProfileSettingContract.View view;
    private ProfileSettingContract.Interactor interactor;

    public ProfileSettingPresenter(ProfileSettingActivity view, ProfileSettingInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void updateProfile() {

    }
}
