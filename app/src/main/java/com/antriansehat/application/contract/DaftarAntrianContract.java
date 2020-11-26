package com.antriansehat.application.contract;

import com.antriansehat.application.api_response.DaftarAntrianResponse;
import com.antriansehat.application.api_response.RegisterResponse;
import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.ScheduleOfHA;

public interface DaftarAntrianContract {

    public interface View {
        void registerSuccess(String message);
        void registerFailed(String message);
    }

    public interface Presenter {
        void register(String residence_number, String polyclinic, String health_agency);
    }

    public interface Interactor {
        void requestSelectedSchedule(ScheduleOfHA scheduleOfHA);
        void getResidenceNumber(String id);
        void requestRegister(String residence_number, String polyclinic, String health_agency, RequestCallback<DaftarAntrianResponse> daftarAntrianResponseRequestCallback);
    }
}
