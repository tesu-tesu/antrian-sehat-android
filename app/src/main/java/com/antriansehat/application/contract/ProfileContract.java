package com.antriansehat.application.contract;

public interface ProfileContract {
    interface View {
    }

    interface Presenter {
        void logout();
    }

    interface Interactor {
        boolean isUserLogin();
        void deleteToken();
    }
}
