package com.antriansehat.application.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.adapter.ListBookAdapter;
import com.antriansehat.application.adapter.ListHealthAgencyAdapter;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.databinding.ActivityPuskesmasListBinding;
import com.antriansehat.application.interactor.ListBookInteractor;
import com.antriansehat.application.interactor.ListHealthAgencyInteractor;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.presenter.ListBookPresenter;
import com.antriansehat.application.presenter.ListHealthAgencyPresenter;
import com.antriansehat.application.util.UtilProvider;

import java.util.List;

public class ListHealthAgencyActivity extends AppCompatActivity implements ListHealthAgencyContract.View, View.OnClickListener {
    private ActivityPuskesmasListBinding binding;
    private ListHealthAgencyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuskesmasListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListHealthAgencyPresenter(this, new ListHealthAgencyInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
        presenter.getHealthAgency();
    }

    private void initView(){
        binding.rvListHealthAgencies.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rvListHealthAgencies.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvListHealthAgencies.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListHealthAgencies(List<HealthAgency> healthAgencies) {
        binding.rvListHealthAgencies.setAdapter(new ListHealthAgencyAdapter(healthAgencies, getLayoutInflater()));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }
}
