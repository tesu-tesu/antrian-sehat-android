package com.antriansehat.application.presenter;

import com.antriansehat.application.contract.ProfileContract;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view, ProfileContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkIsUserLogin() {
        if(interactor.isUserLogin()){
            view.whenUserLogin();
        }
        else {
            view.whenUserNotLogin();
        }
    }

    @Override
    public void logout() {
        interactor.deleteToken();
    }
}
