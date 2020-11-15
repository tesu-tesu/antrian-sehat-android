package com.antriansehat.application.contract;

public interface MainContract {
    interface View {
        void whenUserLogin();
        void whenUserNotLogin();
    }

    interface Presenter {
        void checkIsUserLogin();
    }

    interface Interactor {
        boolean isUserLogin();
    }
}
