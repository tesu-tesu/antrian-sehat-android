package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.R;
import com.antriansehat.application.adapter.ListHealthAgencyAdapter;
import com.antriansehat.application.adapter.ListPolyclinicAdapter;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.databinding.ActivityListPolyBinding;
import com.antriansehat.application.interactor.ListPolyclinicInteractor;
import com.antriansehat.application.model.Polyclinic;
import com.antriansehat.application.presenter.ListPolyclinicPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListPolyclinicActivity extends AppCompatActivity implements ListPolyclinicContract.View,
        View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        ListPolyclinicAdapter.ListPolyclinicListener,
        BaseAuthenticatedView {
    private ActivityListPolyBinding binding;
    private ListPolyclinicPresenter presenter;
    private boolean isFromHA = false;
    private String nextPage;
    private String prevPage;

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
            presenter.getPolyclinic(1);
            binding.btPrev.setVisibility(View.VISIBLE);
            binding.btNext.setVisibility(View.VISIBLE);
        }else{
            this.isFromHA = true;
            presenter.getPolyclinicFromHA(idHa);
            binding.btPrev.setVisibility(View.GONE);
            binding.btNext.setVisibility(View.GONE);
        }

        binding.rvListPoly.setLayoutManager(new GridLayoutManager(this, 2));
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.btBack.setOnClickListener(this);
        binding.btPrev.setOnClickListener(this);
        binding.btNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        }else if(view.getId() == binding.btPrev.getId()){
            presenter.getPolyclinic(Integer.parseInt(prevPage));
        }else if(view.getId() == binding.btNext.getId()){
            presenter.getPolyclinic(Integer.parseInt(nextPage));
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
    public void showListPolyclinics(List<Polyclinic> polyclinics) {
        ListPolyclinicAdapter listPolyclinicAdapter = new ListPolyclinicAdapter(polyclinics, getLayoutInflater());
        binding.rvListPoly.setAdapter(listPolyclinicAdapter);

        listPolyclinicAdapter.setListPolyclinicClickListener(this); //ListHealthAgencyAdapter.ListHealthAgencyListener
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

    @Override
    public void onCardClick(Polyclinic polyclinic) {
        if(!isFromHA){
            Intent healthAgencyPage = new Intent(ListPolyclinicActivity.this,ListHealthAgencyActivity.class);
            healthAgencyPage.putExtra("idPoly", polyclinic.getId());
            startActivity(healthAgencyPage);
        }else{
            Intent healthAgencyPage = new Intent(ListPolyclinicActivity.this,ListScheduleActivity.class);
            healthAgencyPage.putExtra("idPoly", polyclinic.getId());
            healthAgencyPage.putExtra("idHA", getIntent().getStringExtra("idHA"));
            startActivity(healthAgencyPage);
        }
    }
}
