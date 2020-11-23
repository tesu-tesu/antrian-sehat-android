package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.R;
import com.antriansehat.application.adapter.ListHealthAgencyAdapter;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.databinding.ActivityPuskesmasListBinding;
import com.antriansehat.application.interactor.ListHealthAgencyInteractor;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.presenter.ListHealthAgencyPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListHealthAgencyActivity extends AppCompatActivity implements ListHealthAgencyContract.View, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, BaseAuthenticatedView {
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
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        }
    }

    private void goToBackPage() {
        finish();
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
    public void showListHealthAgencies(PaginationHealthAgency pagination) {
        binding.rvListHealthAgencies.setAdapter(new ListHealthAgencyAdapter(pagination.getData(), getLayoutInflater()));
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
                Intent homePage = new Intent(ListHealthAgencyActivity.this,HomeActivity.class);
                startActivity(homePage);
                break;
            case R.id.action_user:
                Intent profilePage = new Intent(ListHealthAgencyActivity.this,ProfileActivity.class);
                startActivity(profilePage);
                break;
            case R.id.action_time:
                Intent historyPage = new Intent(ListHealthAgencyActivity.this,RiwayatTiketActivity.class);
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
