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
import com.antriansehat.application.adapter.ListPolyclinicAdapter;
import com.antriansehat.application.contract.ListPolyclinicContract;
import com.antriansehat.application.databinding.ActivityListPolyBinding;
import com.antriansehat.application.interactor.ListPolyclinicInteractor;
import com.antriansehat.application.model.PaginationHealthAgency;
import com.antriansehat.application.model.PaginationPolyclinic;
import com.antriansehat.application.presenter.ListPolyclinicPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        presenter.getPolyclinic();
    }

    private void initView(){
        binding.recyclerViewTodoList.setLayoutManager(new LinearLayoutManager(this));
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
        binding.recyclerViewTodoList.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        binding.recyclerViewTodoList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListPolyclinics(PaginationPolyclinic pagination) {
        binding.recyclerViewTodoList.setAdapter(new ListPolyclinicAdapter(pagination.getData(), getLayoutInflater()));
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
