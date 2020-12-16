package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.User;

public interface ProfileContract {
    interface View {
        void showUserData(User user);
    }

    interface Presenter {
        void logout();
        void setName();
    }

    interface Interactor {
        void deleteToken();
        void setDataUser(final RequestCallback<User> requestCallback);
    }
}
