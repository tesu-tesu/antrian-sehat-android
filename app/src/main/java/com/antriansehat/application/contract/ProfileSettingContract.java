package com.antriansehat.application.contract;

import com.antriansehat.application.api_response.ProfileSettingResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;

public interface ProfileSettingContract {
    interface View {
        void startLoading();
        void endLoading();
        void onSaveUpdateClick();
        void updateSuccess(String message);
        void updateFailed(String message);
    }

    interface Presenter {
        void updateProfile(String name, String email, String nik);
    }

    interface Interactor {
        void requestUpdateProfile(String name, String email, String nik, RequestCallback<ProfileSettingResponse> requestCallback);
    }
}
