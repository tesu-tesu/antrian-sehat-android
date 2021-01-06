package com.antriansehat.application.contract;

import com.antriansehat.application.api_response.ProfileSettingResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.User;

import java.io.File;

public interface ProfileSettingContract {
    interface View {
        void startLoading();
        void endLoading();
        void onSaveUpdateClick();
        void updateSuccess(String message);
        void updateFailed(String message);
        void showUserData(User user);
        void updateProfileImageSuccess(String message, String path);
    }

    interface Presenter {
        void setUserData();
        void updateProfile(String name, String email, String nik, String phone);
        void updateProfileImage(File imageFile);
    }

    interface Interactor {
        void setDataUser(final RequestCallback<User> requestCallback);
        void requestUpdateProfile(String id, String name, String email, String nik, String phone, RequestCallback<User> requestCallback);
        void updateProfileImage(String id, File imageFile, final RequestCallback<String> requestCallback);
    }
}
