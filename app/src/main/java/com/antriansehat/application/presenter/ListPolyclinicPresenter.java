package com.antriansehat.application.presenter;

import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.contract.ListPolyclinicContract;

public class ListPolyclinicPresenter implements ListPolyclinicContract.Presenter {
    private ListPolyclinicContract.View view;
    private ListPolyclinicContract.Interactor interactor;

    public ListPolyclinicPresenter(ListPolyclinicContract.View view, ListPolyclinicContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getPolyclinic() {

    }
}
