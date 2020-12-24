package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.adapter.ListScheduleAdapter;
import com.antriansehat.application.contract.ListScheduleContract;
import com.antriansehat.application.databinding.ActivityScheduleBinding;
import com.antriansehat.application.interactor.ListScheduleInteractor;
import com.antriansehat.application.model.Schedule;
import com.antriansehat.application.model.ScheduleOfHA;
import com.antriansehat.application.presenter.ListSchedulePresenter;
import com.antriansehat.application.util.UtilProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListScheduleActivity extends AppCompatActivity implements ListScheduleContract.View, View.OnClickListener{
    private ActivityScheduleBinding binding;
    private ListSchedulePresenter presenter;
    private Schedule choosedSchedule = null;
    private String choice = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListSchedulePresenter(this, new ListScheduleInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView(){
        Intent intent = getIntent();
        presenter.getScheduleOfHA(intent.getStringExtra("idHA"), intent.getStringExtra("idPoly"));
        binding.rvListSchedule.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.btBack.setOnClickListener(this);
        binding.btChoice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        }else if(view.getId() == binding.btChoice.getId())
            redirectToRegister();
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rvListSchedule.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvListSchedule.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListSchedule(ScheduleOfHA scheduleOfHA) {
        binding.tvPuskesmas.setText(scheduleOfHA.getHealth_agency().getName());
        binding.tvPolyclinic.setText(scheduleOfHA.getPoly_master().getName());
        binding.tvAddress.setText(scheduleOfHA.getHealth_agency().getAddress());
        binding.tvDate.setText(new SimpleDateFormat(" EEEE, dd MMMM yyyy").format(new Date()));

        final ListScheduleAdapter listScheduleAdapter = new ListScheduleAdapter(scheduleOfHA.getSorted(), getLayoutInflater());
        binding.rvListSchedule.setAdapter(listScheduleAdapter);
        listScheduleAdapter.setListScheduleClickListener(new ListScheduleAdapter.ListScheduleListener() {
            @Override
            public void onCardClick(Schedule schedule) {
                if(schedule.getId() != null)
                    setChoosedSchedule(schedule);
            }
        });
    }

    private void setChoosedSchedule(Schedule schedule) {
        this.choosedSchedule = schedule;
    }

    private void redirectToRegister() {
        if (choosedSchedule != null){
            Intent registerWaitingList = new Intent(ListScheduleActivity.this, DaftarAntrianActivity.class);
            registerWaitingList.putExtra("idSchedule", choosedSchedule.getId());
            registerWaitingList.putExtra("date", choosedSchedule.getDate());
            startActivity(registerWaitingList);
        }
    }

    @Override
    public void showError(String errorMessage) {

    }
}
