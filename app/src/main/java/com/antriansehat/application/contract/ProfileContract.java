package com.antriansehat.application.contract;

public interface ProfileContract {
    interface View {
        void whenUserLogin();
        void whenUserNotLogin();
    }

    interface Presenter {
        void checkIsUserLogin();
        void logout();
    }

    interface Interactor {
        boolean isUserLogin();
        void deleteToken();
    }
}
