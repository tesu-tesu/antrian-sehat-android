package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;

import java.util.List;

public interface ListHealthAgencyContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListHealthAgencies(PaginationHealthAgency pagination);
        void showError(String errorMessage);
    }

    interface Presenter {
        void getHealthAgency();
    }

    interface Interactor {
        void requestListHealthAgency(RequestCallback<PaginationHealthAgency> requestCallback);
    }
}
