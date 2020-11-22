package com.antriansehat.application.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.HomeContract;
import com.antriansehat.application.databinding.ActivityHomeBinding;
import com.antriansehat.application.interactor.HomeInteractor;
import com.antriansehat.application.model.Article;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.HomePresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;
import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, BaseAuthenticatedView {
    private HomeContract.Presenter presenter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferencesUtil()));

        presenter.checkIsUserLogin();
        initView();
    }

    private void initView() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.btCreateWL.setOnClickListener(this);
        binding.btListHA.setOnClickListener(this);
        binding.btListPoly.setOnClickListener(this);
        binding.btShowTicket.setOnClickListener(this);
        binding.cWaitingList.setOnClickListener(this);
    }
  
    @Override
    public void whenUserLogin() {
        presenter.requestNearestWaitingList();
//        finish();
//        startActivity(new Intent(getApplicationContext(), ListBookActivity.class));
        //loading data from API to show in home
    }

    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void showNearestWaitingList(WaitingList waitingList) {
        binding.tvCurrentWaitingList.setText(waitingList.getCurrent_number());
        binding.tvLatestWaitingList.setText(waitingList.getLatest_number());
        binding.tvOrderNumber.setText(waitingList.getOrder_number());
        binding.tvResidenceNumber.setText(waitingList.getResidence_number());
        binding.tvPoly.setText(waitingList.getPolyclinic());
        binding.tvHA.setText(waitingList.getHealth_agency());
        binding.tvRegDate.setText(waitingList.getRegistered_date());
        binding.leftLabel.setVisibility(View.VISIBLE);
        binding.rightLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListArticle(List<Article> articles) {
        //
    }

    @Override
    public void showError(String errorMessage) {
        binding.tvErrorMessage.setText(errorMessage);
        binding.tvErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.cWaitingList.getId()){
            onCardNearestWaitingList();
        }else if(view.getId() == binding.btShowTicket.getId()){
            onButtonShowTicket();
        }else if(view.getId() == binding.btListPoly.getId()){
            onButtonListPoly();
        }else if(view.getId() == binding.btListHA.getId()){
            onButtonListHA();
        }else if(view.getId() == binding.btCreateWL.getId()){
            onButtonCreateWaitingList();
        }
    }

    public void onCardNearestWaitingList() {
        //tampilin fragment ticket dengan barcode
    }

    public void onButtonShowTicket() {
        Intent intent = new Intent(this, ShowTicketActivity.class);
        startActivity(intent);
    }

    public void onButtonListPoly() {
        Intent intent = new Intent(this, ListPolyclinicActivity.class);
        startActivity(intent);
    }

    public void onButtonListHA() {
        Intent intent = new Intent(this, ListHealthAgencyActivity.class);
        startActivity(intent);
    }

    public void onButtonCreateWaitingList() {
        Intent intent = new Intent(this, RiwayatTiketActivity.class);
        startActivity(intent);
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                break;
            case R.id.action_user:
                Intent profile = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.action_time:
                Intent time = new Intent(HomeActivity.this,RiwayatTiketActivity.class);
                startActivity(time);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }
}
