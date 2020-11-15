package com.antriansehat.application.presenter;

import com.antriansehat.application.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainContract.Interactor interactor;

    public MainPresenter(MainContract.View view, MainContract.Interactor interactor) {
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
