package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.Article;
import com.antriansehat.application.model.UserWaitingList;
import com.antriansehat.application.model.WaitingList;

import java.util.List;
import java.util.Map;

public interface RiwayatTiketContract {
    int PAST_TICKET = 0;
    int TODAY_TICKET = 1;
    int FUTURE_TICKET = 2;

    interface View {
        void startLoading();
        void endLoading();
        void showWaitingList(List<WaitingList> waitingLists);
        void showError(String errorMessage);
        void showTicket(WaitingList waitingList);
    }

    interface Presenter {
        void requestTicket(int ticketType);
    }

    interface Interactor {
        void requestTicket(int ticketType, RequestCallback<List<WaitingList>> requestCallback);
    }
}
