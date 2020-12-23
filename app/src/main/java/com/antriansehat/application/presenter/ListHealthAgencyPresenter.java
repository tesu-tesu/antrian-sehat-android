package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Pagination;

import java.util.List;

public class ListHealthAgencyPresenter implements ListHealthAgencyContract.Presenter {
    private ListHealthAgencyContract.View view;
    private ListHealthAgencyContract.Interactor interactor;

    public ListHealthAgencyPresenter(ListHealthAgencyContract.View view, ListHealthAgencyContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getHealthAgency(int page) {
        view.startLoading();
        interactor.requestListHealthAgency(new RequestCallback<Pagination<HealthAgency>>() {
            @Override
            public void requestSuccess(Pagination<HealthAgency> data) {
                view.endLoading();
                view.setNextPage(data.getNext_page_url());
                view.setPrevPage(data.getPrev_page_url());
                view.showListHealthAgencies(data.getData());
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, page);
    }

    @Override
    public void getHealthAgencyOfPolyId(String idPoly) {
        view.startLoading();
        interactor.requestListHealthAgencyOfPolyId(new RequestCallback<List<HealthAgency>>() {
            @Override
            public void requestSuccess(List<HealthAgency> data) {
                view.endLoading();
                view.showListHealthAgencies(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, idPoly);
    }

}
