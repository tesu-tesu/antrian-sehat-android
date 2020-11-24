package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;

import java.util.List;

public interface ListPolyclinicContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListPolyclinics(PaginationPolyclinic pagination);
        void showListPolyclinics(List<Polyclinic> data);
        void showError(String errorMessage);
    }

    interface Presenter {
        void getPolyclinic();
        void getPolyclinicFromHA(String id);
    }

    interface Interactor {
        void requestListPolyclinic(RequestCallback<PaginationPolyclinic> requestCallback);
        void requestListPolyclinic(RequestCallback<List<PolymasterFromSelectedHA>> requestCallback, String idHA);
    }
}
