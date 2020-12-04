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
    public void getScheduleOfHA(String idHA, final String idPoly) {
        view.startLoading();
        interactor.requestListScheduleOfHA(new RequestCallback<List<ScheduleOfHA>>() {
            @Override
            public void requestSuccess(List<ScheduleOfHA> scheduleOfHA) {
                view.endLoading();
                view.showListSchedule(newListScheduleOfHA(scheduleOfHA, idPoly));
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, idHA);
    }

    private ScheduleOfHA newListScheduleOfHA(List<ScheduleOfHA> scheduleOfHA, String idPoly){
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        String[] charOfDay = new String[]{"M", "S", "S", "R", "K", "J", "S"};

        ScheduleOfHA selectedScheduleOfHA = getSelectedScheduleOfHA(scheduleOfHA, idPoly);

        for(int i = 0 ; i < 7 ; i++){
            boolean isFound = false;
            if(selectedScheduleOfHA != null){
                for(Schedule schedule : selectedScheduleOfHA.getSorted()){
                    if(schedule.getDay().equals(String.valueOf(i))){
                        scheduleList.add(schedule);
                        isFound = true;
                        break;
                    }
                }
            }
            if(!isFound)
                scheduleList.add(new Schedule(String.valueOf(i), "-", "-", charOfDay[i]));
        }

        ScheduleOfHA newScheduleOfHA = new ScheduleOfHA();

        newScheduleOfHA.setId(selectedScheduleOfHA.getId());
        newScheduleOfHA.setPoly_master_id(selectedScheduleOfHA.getPoly_master_id());
        newScheduleOfHA.setPoly_master(selectedScheduleOfHA.getPoly_master());
        newScheduleOfHA.setHealth_agency_id(selectedScheduleOfHA.getHealth_agency_id());
        newScheduleOfHA.setHealth_agency(selectedScheduleOfHA.getHealth_agency());
        newScheduleOfHA.setSorted(scheduleList);

        return newScheduleOfHA;
    }

    private ScheduleOfHA getSelectedScheduleOfHA(List<ScheduleOfHA> scheduleOfHA, String idPoly){
        for(ScheduleOfHA schedule : scheduleOfHA)
            if(schedule.getPoly_master_id().equals(idPoly))
                return schedule;

        return null;
    }
}
