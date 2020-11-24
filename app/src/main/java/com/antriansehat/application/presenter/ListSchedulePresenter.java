package com.antriansehat.application.presenter;

import android.util.Log;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.ListScheduleContract;
import com.antriansehat.application.model.Schedule;
import com.antriansehat.application.model.ScheduleOfHA;

import java.util.ArrayList;
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
                view.showListSchedule(newListScheduleOfHA(scheduleOfHA));
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

    private ScheduleOfHA newListScheduleOfHA(List<ScheduleOfHA> scheduleOfHA){
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        String[] charOfDay = new String[]{"M", "S", "S", "R", "K", "J", "S"};
        for(int i = 0 ; i < 7 ; i++){
            boolean isFound = false;
            for(Schedule schedule : scheduleOfHA.get(0).getSorted()){
                if(schedule.getDay().equals(String.valueOf(i))){
                    scheduleList.add(schedule);
                    isFound = true;
                    break;
                }
            }
            if(!isFound)
                scheduleList.add(new Schedule(String.valueOf(i), "-", "-", charOfDay[i]));
        }

        ScheduleOfHA newScheduleOfHA = new ScheduleOfHA();
        for(ScheduleOfHA schedule : scheduleOfHA){
            newScheduleOfHA.setId(schedule.getId());
            newScheduleOfHA.setPoly_master_id(schedule.getPoly_master_id());
            newScheduleOfHA.setPoly_master(schedule.getPoly_master());
            newScheduleOfHA.setHealth_agency_id(schedule.getHealth_agency_id());
            newScheduleOfHA.setHealth_agency(schedule.getHealth_agency());
            newScheduleOfHA.setSorted(scheduleList);
        }

        return newScheduleOfHA;
    }
}
