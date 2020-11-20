package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Pagination;

import java.util.List;

public interface ListHealthAgencyContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListHealthAgencies(Pagination pagination);
        void showError(String errorMessage);
    }

    interface Presenter {
        void getHealthAgency();
    }

    interface Interactor {
        void requestListHealthAgency(RequestCallback<Pagination> requestCallback);
    }
}
