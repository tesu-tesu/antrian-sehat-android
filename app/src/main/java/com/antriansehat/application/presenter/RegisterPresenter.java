package com.antriansehat.application.presenter;

import android.util.Log;
import android.widget.Toast;

import com.antriansehat.application.api_response.LoginResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.RegisterContract;

public class RegisterPresenter implements RegisterContract.Presenter{
    private RegisterContract.View view;
    private RegisterContract.Interactor interactor;

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(String name, String phone, String email, String password, String confirmPassword) {
        view.startLoading();
        interactor.requestRegister(name, phone, email, password, confirmPassword, new RequestCallback<RegisterResponse>() {
            @Override
            public void requestSuccess(RegisterResponse data) {
                view.endLoading();
                view.registerSuccess(data.message);
                interactor.saveToken(data.access_token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.registerFailed(errorMessage);
            }
        });
    }
}
