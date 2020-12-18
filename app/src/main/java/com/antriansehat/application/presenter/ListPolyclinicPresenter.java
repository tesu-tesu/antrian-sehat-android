package com.antriansehat.application.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.model.Pagination;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;

import java.util.ArrayList;
import java.util.List;

public class ListPolyclinicPresenter implements ListPolyclinicContract.Presenter {
    private ListPolyclinicContract.View view;
    private ListPolyclinicContract.Interactor interactor;

    public ListPolyclinicPresenter(ListPolyclinicContract.View view, ListPolyclinicContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getPolyclinic(@Nullable int page) {
        view.startLoading();
        interactor.requestListPolyclinic(new RequestCallback<Pagination<Polyclinic>>() {
            @Override
            public void requestSuccess(Pagination<Polyclinic> data) {
                view.endLoading();
                view.setNextPage(data.getNext_page_url());
                view.setPrevPage(data.getPrev_page_url());
                view.showListPolyclinics(data.getData());
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, page);
    }

    @Override
    public void getPolyclinicFromHA(String idHA) {
        view.startLoading();
        interactor.requestListPolyclinic(new RequestCallback<List<PolymasterFromSelectedHA>>() {
            @Override
            public void requestSuccess(List<PolymasterFromSelectedHA> data) {
                view.endLoading();

                List<Polyclinic> polyclinics = new ArrayList<>();
                for (PolymasterFromSelectedHA poly:data) {
                    polyclinics.add(poly.getPolyMaster());
                }
                view.showListPolyclinics(polyclinics);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, idHA);
    }

}
