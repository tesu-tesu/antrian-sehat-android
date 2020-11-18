package com.antriansehat.application.presenter;

import com.antriansehat.application.contract.HomeContract;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private HomeContract.Interactor interactor;

    public HomePresenter(HomeContract.View view, HomeContract.Interactor interactor) {
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
}
