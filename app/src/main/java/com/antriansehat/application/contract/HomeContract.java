package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.Article;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public interface HomeContract {
    interface View {
        void whenUserLogin();
        void whenUserNotLogin();
        void showNearestWaitingList(WaitingList waitingList);
        void showListArticle(List<Article> articles);
        void showError(String errorMessage);
    }

    interface Presenter {
        void checkIsUserLogin();
        void requestNearestWaitingList();
        void requestListArticle();
        void logout();
    }

    interface Interactor {
        boolean isUserLogin();
        void requestNearestWaitingList(RequestCallback<WaitingList> requestCallback);
        void requestArticle(RequestCallback<List<Article>> requestCallback);
        void logout();
    }

}
