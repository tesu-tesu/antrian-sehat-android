package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.model.User;

import java.util.List;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view, ProfileContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void logout() {
        interactor.deleteToken();
    }

    @Override
    public void requestBookedResidenceNumber() {
        interactor.requestBookedResidenceNumber(new RequestCallback<List<String>>() {
            @Override
            public void requestSuccess(List<String> data) {
                view.showBookedResidenceNumbers(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorGetResidenceNumbers(errorMessage);
                System.out.println("error " + errorMessage);
            }
        });
    }

    @Override
    public void setDataUser() {
        interactor.setDataUser(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                view.showUserData(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                System.out.println("GAGAL");
            }
        });
    }
}
