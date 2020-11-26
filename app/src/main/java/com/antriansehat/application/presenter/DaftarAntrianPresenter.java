package com.antriansehat.application.presenter;

import com.antriansehat.application.api_response.DaftarAntrianResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.DaftarAntrianContract;

public class DaftarAntrianPresenter implements DaftarAntrianContract.Presenter {
    private DaftarAntrianContract.View view;
    private DaftarAntrianContract.Interactor interactor;

    public DaftarAntrianPresenter(DaftarAntrianContract.View view, DaftarAntrianContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void register(String residence_number, String polyclinic, String health_agency) {
        interactor.requestRegister(residence_number, polyclinic, health_agency, new RequestCallback<DaftarAntrianResponse>() {
            @Override
            public void requestSuccess(DaftarAntrianResponse data) {

            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }
}
