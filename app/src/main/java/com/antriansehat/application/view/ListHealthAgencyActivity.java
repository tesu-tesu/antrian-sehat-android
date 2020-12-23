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
import com.antriansehat.application.adapter.ListHealthAgencyAdapter;
import com.antriansehat.application.contract.ListHealthAgencyContract;
import com.antriansehat.application.databinding.ActivityPuskesmasListBinding;
import com.antriansehat.application.interactor.ListHealthAgencyInteractor;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.presenter.ListHealthAgencyPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListHealthAgencyActivity extends AppCompatActivity implements ListHealthAgencyContract.View,
        View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        ListHealthAgencyAdapter.ListHealthAgencyListener,
        BaseAuthenticatedView {
    private ActivityPuskesmasListBinding binding;
    private ListHealthAgencyPresenter presenter;
    private boolean isFromPoly = false;
    private String nextPage;
    private String prevPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuskesmasListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListHealthAgencyPresenter(this, new ListHealthAgencyInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView(){
        Intent intent = getIntent();
        String idPoly = intent.getStringExtra("idPoly");

        if (idPoly == null){
            presenter.getHealthAgency(1);
            binding.btPrev.setVisibility(View.VISIBLE);
            binding.btNext.setVisibility(View.VISIBLE);
        }else{
            this.isFromPoly = true;
            presenter.getHealthAgencyOfPolyId(idPoly);
            binding.btPrev.setVisibility(View.GONE);
            binding.btNext.setVisibility(View.GONE);
        }

        binding.rvListHealthAgencies.setLayoutManager(new LinearLayoutManager(this));
        binding.bottomNav.setOnNavigationItemSelectedListener(this);    //BottomNavigationView.OnNavigationItemSelectedListener
        binding.btBack.setOnClickListener(this);
        binding.btPrev.setOnClickListener(this);
        binding.btNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        }else if(view.getId() == binding.btPrev.getId()){
            System.out.println("bef: "+ prevPage);
            presenter.getHealthAgency(Integer.parseInt(prevPage));
        }else if(view.getId() == binding.btNext.getId()){
            System.out.println("neks: "+ nextPage);
            presenter.getHealthAgency(Integer.parseInt(nextPage));
        }
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
        ListHealthAgencyAdapter listHealthAgencyAdapter = new ListHealthAgencyAdapter(healthAgencies, getLayoutInflater());
        binding.rvListHealthAgencies.setAdapter(listHealthAgencyAdapter);

        listHealthAgencyAdapter.setListHealthAgencyClickListener(this); //ListHealthAgencyAdapter.ListHealthAgencyListener
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPrevPage(String prevPage) {
        if(prevPage != null){
            binding.btPrev.setVisibility(View.VISIBLE);
            String[] arr =  prevPage.split("=");
            this.prevPage = arr[1];
        }else{
            binding.btPrev.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setNextPage(String nextPage) {
        if(nextPage != null){
            binding.btNext.setVisibility(View.VISIBLE);
            String[] arr =  nextPage.split("=");
            this.nextPage = arr[1];
        }else{
            binding.btNext.setVisibility(View.INVISIBLE);
        }
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

    @Override
    public void onCardClick(HealthAgency healthAgency) {
        if(!isFromPoly){
            Intent polyclinicPage = new Intent(ListHealthAgencyActivity.this,ListPolyclinicActivity.class);
            polyclinicPage.putExtra("idHA", healthAgency.getId());
            startActivity(polyclinicPage);
        }else{
            Intent polyclinicPage = new Intent(ListHealthAgencyActivity.this,ListScheduleActivity.class);
            polyclinicPage.putExtra("idHA", healthAgency.getId());
            polyclinicPage.putExtra("idPoly", getIntent().getStringExtra("idPoly"));
            startActivity(polyclinicPage);
        }
    }
}
