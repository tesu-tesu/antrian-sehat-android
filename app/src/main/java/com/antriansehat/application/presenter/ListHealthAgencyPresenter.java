package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.model.PaginationHealthAgency;

public class ListHealthAgencyPresenter implements ListHealthAgencyContract.Presenter {
    private ListHealthAgencyContract.View view;
    private ListHealthAgencyContract.Interactor interactor;

    public ListHealthAgencyPresenter(ListHealthAgencyContract.View view, ListHealthAgencyContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getHealthAgency() {
        view.startLoading();
        interactor.requestListHealthAgency(new RequestCallback<PaginationHealthAgency>() {
            @Override
            public void requestSuccess(PaginationHealthAgency data) {
                view.endLoading();
                view.showListHealthAgencies(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
