package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.User;

import java.util.List;

public interface ProfileContract {

    interface View {
        void showUserData(User user);
        void showErrorGetResidenceNumbers(String errorMessage);
        void showBookedResidenceNumbers(List<String> residenceNumbers);
    }

    interface Presenter {
        void logout();
        void requestBookedResidenceNumber();
        void setDataUser();
    }

    interface Interactor {
        void deleteToken();
        void setDataUser(final RequestCallback<User> requestCallback);
        void requestBookedResidenceNumber(final RequestCallback<List<String>> requestCallback);
    }
}
