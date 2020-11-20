package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Polyclinic;

import java.util.List;

public interface ListPolyclinicContract {
    interface View {
        void startLoading();
        void endLoading();
        void showPolyclinics(List<Polyclinic> polyclinics);
        void showError(String errorMessage);
    }

    interface Presenter {
        void getPolyclinic();
    }

    interface Interactor {
        void requestListPolyclinic(RequestCallback<List<Polyclinic>> requestCallback);
    }
}
