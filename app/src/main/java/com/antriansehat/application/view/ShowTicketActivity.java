package com.antriansehat.application.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.contract.ShowTicketContract;
import com.antriansehat.application.databinding.ActivityShowTicketBinding;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.ShowTicketPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShowTicketActivity extends AppCompatActivity implements ShowTicketContract.View, View.OnClickListener{
    private ShowTicketContract.Presenter presenter;
    private ActivityShowTicketBinding binding;
    private WaitingList waitingList;
    private boolean newTicket;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ShowTicketPresenter(this);
        waitingList = (WaitingList) getIntent().getExtras().get("waitinglist");

        initView();
    }

    private void initView() {
        newTicket = getIntent().getExtras().get("newTicket") != null ? true : false;
        presenter.requestQRCode(waitingList);
        binding.btBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btBack.getId()){
            backToHome();
        }
    }

    @Override
    public void onBackPressed() {
        backToHome();
    }

    private void backToHome() {
        if(newTicket) {
            Intent home = new Intent(ShowTicketActivity.this, HomeActivity.class);
            startActivity(home);
        }
        finish();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTicket(Bitmap qrCode) {
        binding.qrCode.setImageBitmap(qrCode);
        binding.setWaitingList(waitingList);
    }
}
