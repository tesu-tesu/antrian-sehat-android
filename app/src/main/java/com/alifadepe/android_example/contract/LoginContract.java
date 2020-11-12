package com.alifadepe.android_example.contract;

public interface LoginContract {
    interface View {
        void startLoading();
        void endLoading();
        void loginSuccess();
        void loginFailed(String message);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
