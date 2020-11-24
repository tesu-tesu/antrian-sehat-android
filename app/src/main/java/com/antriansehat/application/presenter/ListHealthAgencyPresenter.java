package com.antriansehat.application.presenter;

import android.util.Log;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;

import java.util.List;

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
