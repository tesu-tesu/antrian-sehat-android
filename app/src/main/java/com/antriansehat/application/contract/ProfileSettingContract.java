package com.antriansehat.application.contract;

public interface ProfileSettingContract {
    interface View {
        void saveUpdate();
    }

    interface Presenter {
        void updateProfile();
    }

    interface Interactor {
        void updateProfile();
    }
}
