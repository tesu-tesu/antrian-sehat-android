package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.Pagination;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;

import java.util.List;

public interface ListPolyclinicContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListPolyclinics(List<Polyclinic> data);
        void showError(String errorMessage);
        void setPrevPage(String prevPage);
        void setNextPage(String nextPage);
    }

    interface Presenter {
        void getPolyclinic(int page);
        void getPolyclinicFromHA(String idHA);
    }

    interface Interactor {
        void requestListPolyclinic(RequestCallback<Pagination<Polyclinic>> requestCallback, int page);
        void requestListPolyclinic(RequestCallback<List<PolymasterFromSelectedHA>> requestCallback, String idHA);
    }
}
