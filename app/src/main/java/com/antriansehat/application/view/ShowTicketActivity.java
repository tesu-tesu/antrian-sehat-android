package com.antriansehat.application.view;

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
        presenter.requestQRCode(waitingList);
        binding.btBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btBack.getId()){
            goToBackPage();
        }
    }

    private void goToBackPage() {
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
