package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;

public class ListPolyclinicPresenter implements ListPolyclinicContract.Presenter {
    private ListPolyclinicContract.View view;
    private ListPolyclinicContract.Interactor interactor;

    public ListPolyclinicPresenter(ListPolyclinicContract.View view, ListPolyclinicContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getPolyclinic() {
        view.startLoading();
        interactor.requestListPolyclinic(new RequestCallback<PaginationPolyclinic>() {
            @Override
            public void requestSuccess(PaginationPolyclinic data) {
                view.endLoading();
                view.showListPolyclinics(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
