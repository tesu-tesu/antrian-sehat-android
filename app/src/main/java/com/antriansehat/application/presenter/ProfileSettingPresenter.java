package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.interactor.ProfileSettingInteractor;
import com.antriansehat.application.model.User;
import com.antriansehat.application.view.ProfileSettingActivity;

import java.io.File;

public class ProfileSettingPresenter implements ProfileSettingContract.Presenter {
    private ProfileSettingContract.View view;
    private ProfileSettingContract.Interactor interactor;
    private User currentUser;

    public ProfileSettingPresenter(ProfileSettingActivity view, ProfileSettingInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setUserData() {
        interactor.setDataUser(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                view.endLoading();
                setThisUser(data);
                view.showUserData(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                System.out.println("GAGAL");
            }
        });
    }

    private void setThisUser(User user){
        this.currentUser = user;
    }

    @Override
    public void updateProfile(String name, String email, String nik, String phone) {
        view.startLoading();
        interactor.requestUpdateProfile(currentUser.getId(), name, email, nik, phone, new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                setThisUser(data);
                view.endLoading();
                view.updateSuccess("Your data updated successfully!");
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.updateFailed(errorMessage);
            }
        });
    }

    @Override
    public void updateProfileImage(File imageFile) {
        interactor.updateProfileImage(currentUser.getId(), imageFile, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String path) {
                view.updateProfileImageSuccess("Berhasil mengubah foto profil", path);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.updateFailed(errorMessage);
                System.out.println(errorMessage);
            }
        });
    }
}
