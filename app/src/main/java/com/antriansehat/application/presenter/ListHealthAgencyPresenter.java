package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Pagination;

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
        interactor.requestListHealthAgency(new RequestCallback<Pagination<HealthAgency>>() {
            @Override
            public void requestSuccess(Pagination<HealthAgency> data) {
                view.endLoading();
                view.showListHealthAgencies(data.getData());
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

}
