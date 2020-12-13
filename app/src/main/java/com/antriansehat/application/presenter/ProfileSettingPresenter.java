package com.antriansehat.application.presenter;

import com.antriansehat.application.api_response.ProfileSettingResponse;
import com.antriansehat.application.callback.RequestCallback;
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
    public void updateProfile(String name, String email, String nik) {
        view.startLoading();
        interactor.requestUpdateProfile(name, email, nik, new RequestCallback<ProfileSettingResponse>() {
            @Override
            public void requestSuccess(ProfileSettingResponse data) {
                view.endLoading();
                view.updateSuccess(data.message);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.updateFailed(errorMessage);
            }
        });
    }
}
