package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.R;
import com.antriansehat.application.adapter.ListPolyclinicAdapter;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.databinding.ActivityListPolyBinding;
import com.antriansehat.application.interactor.ListPolyclinicInteractor;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.model.PolymasterFromSelectedHA;
import com.antriansehat.application.presenter.ListPolyclinicPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListPolyclinicActivity extends AppCompatActivity implements ListPolyclinicContract.View, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, BaseAuthenticatedView {
    private ActivityListPolyBinding binding;
    private ListPolyclinicPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPolyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListPolyclinicPresenter(this, new ListPolyclinicInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView(){
        Intent intent = getIntent();
        String idHa = intent.getStringExtra("idHA");
        if (idHa == null){
            presenter.getPolyclinic();
        }else{
            presenter.getPolyclinicFromHA(idHa);
        }

        binding.rvListPoly.setLayoutManager(new LinearLayoutManager(this));
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        }
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rvListPoly.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvListPoly.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListPolyclinics(PaginationPolyclinic pagination) {
        binding.rvListPoly.setAdapter(new ListPolyclinicAdapter(pagination.getData(), getLayoutInflater()));
    }

    @Override
    public void showListPolyclinics(List<Polyclinic> data) {
        binding.rvListPoly.setAdapter(new ListPolyclinicAdapter(data, getLayoutInflater()));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homePage = new Intent(ListPolyclinicActivity.this,HomeActivity.class);
                startActivity(homePage);
                break;
            case R.id.action_user:
                Intent profilePage = new Intent(ListPolyclinicActivity.this,ProfileActivity.class);
                startActivity(profilePage);
                break;
            case R.id.action_time:
                Intent historyPage = new Intent(ListPolyclinicActivity.this,RiwayatTiketActivity.class);
                startActivity(historyPage);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }
}
