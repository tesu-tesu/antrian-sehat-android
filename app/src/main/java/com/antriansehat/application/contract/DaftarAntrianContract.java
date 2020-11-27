package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.model.WaitingListFromSchedule;

import java.util.Date;

public interface DaftarAntrianContract {

    public interface View {
        void startLoading();
        void endLoading();
        void showWaitingList(WaitingListFromSchedule waitingList);
        void setResidenceNumber(String residenceNumber);
        void registerSuccess(String message);
        void registerFailed(String message);
        void showError(String errorMessage);
    }

    public interface Presenter {
        void getResidenceNumber();
        void showWaitingList(String idSchedule, Date date);
        void register(String idSchedule, Date date, String residenceNumber);
    }

    public interface Interactor {
        void requestResidenceNumber(RequestCallback<String> requestCallback);
        void requestWaitingList(String idSchedule, String date, RequestCallback<WaitingListFromSchedule> requestCallback);
        void requestRegister(String idSchedule, String date, String residenceNumber, RequestCallback<WaitingList> requestCallback);
    }
}
