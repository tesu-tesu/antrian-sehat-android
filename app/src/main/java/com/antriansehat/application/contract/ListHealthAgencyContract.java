package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.HealthAgency;

import java.util.List;

public interface ListHealthAgencyContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListHealthAgencies(List<HealthAgency> healthAgencies);
        void showError(String errorMessage);
    }

    interface Presenter {
        void getHealthAgency();
    }

    interface Interactor {
        void requestListHealthAgency(RequestCallback<List<HealthAgency>> requestCallback);
    }
}
