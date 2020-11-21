package com.antriansehat.application.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.databinding.ActivityListPolyBinding;
import com.antriansehat.application.interactor.ListPolyclinicInteractor;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.presenter.ListPolyclinicPresenter;
import com.antriansehat.application.util.UtilProvider;

import java.util.List;

public class ListPolyclinicActivity extends AppCompatActivity implements ListPolyclinicContract.View, View.OnClickListener {
    private ActivityListPolyBinding binding;
    private ListPolyclinicPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPolyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListPolyclinicPresenter(this, new ListPolyclinicInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
        presenter.getPolyclinic();
    }

    private void initView() {
        binding.recyclerViewTodoList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showPolyclinics(List<Polyclinic> polyclinics) {

    }

    @Override
    public void showError(String errorMessage) {

    }
}
