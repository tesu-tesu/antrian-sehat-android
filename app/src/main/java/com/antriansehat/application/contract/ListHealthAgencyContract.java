package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Pagination;

import java.util.List;

public interface ListHealthAgencyContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListHealthAgencies(List<HealthAgency> healthAgencies);
        void showError(String errorMessage);
        void setPrevPage(String prevPage);
        void setNextPage(String nextPage);
    }

    interface Presenter {
        void getHealthAgency(int page);
        void getHealthAgencyOfPolyId(String idPoly);
    }

    interface Interactor {
        void requestListHealthAgency(RequestCallback<Pagination<HealthAgency>> requestCallback, int page);
        void requestListHealthAgencyOfPolyId(RequestCallback<List<HealthAgency>> requestCallback, String poly_id);
    }
}
