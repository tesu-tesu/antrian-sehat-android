package com.antriansehat.application.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.antriansehat.application.R;
import com.antriansehat.application.adapter.RiwayatTiketAkanDatangAdapter;
import com.antriansehat.application.adapter.RiwayatTiketHariIniAdapter;
import com.antriansehat.application.adapter.RiwayatTiketSelesaiAdapter;
import com.antriansehat.application.contract.RiwayatTiketContract;
import com.antriansehat.application.contract.ShowTicketContract;
import com.antriansehat.application.databinding.ActivityRiwayatTiketBinding;
import com.antriansehat.application.interactor.RiwayatTiketInteractor;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.RiwayatTiketPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class RiwayatTiketActivity extends AppCompatActivity implements RiwayatTiketContract.View, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, BaseAuthenticatedView {
    private RiwayatTiketContract.Presenter presenter;
    private ActivityRiwayatTiketBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRiwayatTiketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RiwayatTiketPresenter(this, new RiwayatTiketInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
        presenter.requestTicket();
    }

    private void initView() {
        binding.historyHariIni.setLayoutManager(new LinearLayoutManager(this));
        binding.historyAkanDatang.setLayoutManager(new LinearLayoutManager(this));
        binding.historySelesai.setLayoutManager(new LinearLayoutManager(this));
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
//        binding.btBack.setOnClickListener(this);
        binding.btBack.setVisibility(View.GONE);
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent home = new Intent(RiwayatTiketActivity.this, HomeActivity.class);
                startActivity(home);
                this.finish();
                break;
            case R.id.action_user:
                Intent profile = new Intent(RiwayatTiketActivity.this,ProfileActivity.class);
                startActivity(profile);
                this.finish();
                break;
            case R.id.action_time:
                break;
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if(v.getId() == binding.btBack.getId()){
            goToBackPage();
        }
    }

    private void goToBackPage() {
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.historyHariIni.setVisibility(View.GONE);
        binding.historyAkanDatang.setVisibility(View.GONE);
        binding.historySelesai.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.historyHariIni.setVisibility(View.VISIBLE);
        binding.historyAkanDatang.setVisibility(View.VISIBLE);
        binding.historySelesai.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFutureWaitingList(final List<WaitingList> waitingLists) {
        binding.historyAkanDatang.setAdapter(new RiwayatTiketAkanDatangAdapter(waitingLists, getLayoutInflater()));
        ((RiwayatTiketAkanDatangAdapter) binding.historyAkanDatang.getAdapter()).setOnItemClickListener(new RiwayatTiketAkanDatangAdapter.ItemClickListener() {
            @Override
            public void onItemClick(WaitingList waitingList) {
                showTicket(waitingList);
            }
        });
    }

    @Override
    public void showCurrentWaitingList(List<WaitingList> waitingLists) {
        binding.historyHariIni.setAdapter(new RiwayatTiketHariIniAdapter(waitingLists, getLayoutInflater()));
        ((RiwayatTiketHariIniAdapter) binding.historyHariIni.getAdapter()).setOnItemClickListener(new RiwayatTiketHariIniAdapter.ItemClickListener() {
            @Override
            public void onItemClick(WaitingList waitingList) {
                showTicket(waitingList);
            }
        });
    }

    @Override
    public void showHistoryWaitingList(List<WaitingList> waitingLists) {
        binding.historySelesai.setAdapter(new RiwayatTiketSelesaiAdapter(waitingLists, getLayoutInflater()));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTicket(WaitingList waitingList) {
        Intent showTicket = new Intent(RiwayatTiketActivity.this, ShowTicketActivity.class);
        showTicket.putExtra("waitinglist", waitingList);
        startActivity(showTicket);
    }
}