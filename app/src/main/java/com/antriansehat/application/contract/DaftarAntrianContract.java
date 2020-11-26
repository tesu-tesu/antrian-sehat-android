package com.antriansehat.application.contract;

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
        void requestSchedule(ScheduleOfHA scheduleOfHA);
        void requestRegister(String schedule, String residence_number, String health_agency, String polyclinic, String order_number);
        void getResidenceNumber(String id);
    }
}
