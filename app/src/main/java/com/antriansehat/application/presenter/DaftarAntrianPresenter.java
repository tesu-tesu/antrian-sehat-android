package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.model.WaitingListFromSchedule;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DaftarAntrianPresenter implements DaftarAntrianContract.Presenter {
    private DaftarAntrianContract.View view;
    private DaftarAntrianContract.Interactor interactor;

    public DaftarAntrianPresenter(DaftarAntrianContract.View view, DaftarAntrianContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getResidenceNumber() {
        interactor.requestResidenceNumber(new RequestCallback<String>() {
            @Override
            public void requestSuccess(String data) {
                view.setResidenceNumber(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void showWaitingList(String idSchedule, Date date) {
        String dateString = new SimpleDateFormat("YYYY-MM-dd").format(date);
        interactor.requestWaitingList(idSchedule, dateString, new RequestCallback<WaitingListFromSchedule>() {
            @Override
            public void requestSuccess(WaitingListFromSchedule data) {
                view.showWaitingList(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void register(String idSchedule, Date date, String residenceNumber) {
        String dateString = new SimpleDateFormat("YYYY-MM-dd").format(date);
        interactor.requestRegister(idSchedule, dateString, residenceNumber, new RequestCallback<WaitingList>() {
            @Override
            public void requestSuccess(WaitingList response) {
                view.registerSuccess("Sukses Mendaftar");
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.registerFailed(errorMessage);
            }
        });
    }
}
