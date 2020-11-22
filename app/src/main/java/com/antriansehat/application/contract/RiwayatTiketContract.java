package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.Article;
import com.antriansehat.application.model.UserWaitingList;
import com.antriansehat.application.model.WaitingList;

import java.util.List;
import java.util.Map;

public interface RiwayatTiketContract {
    interface View {
        void startLoading();
        void endLoading();
        void showFutureWaitingList(List<WaitingList> waitingLists);
        void showCurrentWaitingList(List<WaitingList> waitingLists);
        void showHistoryWaitingList(List<WaitingList> waitingLists);
        void showError(String errorMessage);
    }

    interface Presenter {
        void requestTicket();
    }

    interface Interactor {
        void requestTicket(RequestCallback<UserWaitingList> requestCallback);
    }
}
