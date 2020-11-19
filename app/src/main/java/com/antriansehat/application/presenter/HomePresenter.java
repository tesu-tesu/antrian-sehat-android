package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.HomeContract;
import com.antriansehat.application.model.WaitingList;

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

    @Override
    public void requestNearestWaitingList() {
//        view.startLoading();
        interactor.requestNearestWaitingList(new RequestCallback<WaitingList>() {
            @Override
            public void requestSuccess(WaitingList waitingList) {
//                view.endLoading();
                view.showNearestWaitingList(waitingList);
            }

            @Override
            public void requestFailed(String errorMessage) {
//                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void requestListArticle() {

    }

    @Override
    public void logout() {

    }
}
