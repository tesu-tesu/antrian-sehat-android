package com.antriansehat.application.contract;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.model.Schedule;
import com.antriansehat.application.model.ScheduleOfHA;

import java.util.List;

public interface ListScheduleContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListSchedule(List<ScheduleOfHA> scheduleOfHA);
        void showError(String errorMessage);
    }

    interface Presenter {
        void getScheduleOfHA();
    }

    interface Interactor {
        void requestListScheduleOfHA(RequestCallback<List<ScheduleOfHA>> requestCallback);
    }
}
