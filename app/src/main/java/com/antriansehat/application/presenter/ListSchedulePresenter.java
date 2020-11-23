package com.antriansehat.application.presenter;

import android.util.Log;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListScheduleContract;
import com.antriansehat.application.model.Schedule;
import com.antriansehat.application.model.ScheduleOfHA;

import java.util.List;

public class ListSchedulePresenter implements ListScheduleContract.Presenter{
    private ListScheduleContract.View view;
    private ListScheduleContract.Interactor interactor;

    public ListSchedulePresenter(ListScheduleContract.View view, ListScheduleContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getScheduleOfHA() {
        view.startLoading();
        interactor.requestListScheduleOfHA(new RequestCallback<List<ScheduleOfHA>>() {
            @Override
            public void requestSuccess(List<ScheduleOfHA> scheduleOfHA) {
                view.endLoading();
                view.showListSchedule(scheduleOfHA);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
