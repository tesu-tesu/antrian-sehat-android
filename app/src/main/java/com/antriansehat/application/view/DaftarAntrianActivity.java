package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.databinding.ActivityDaftarAntrianBinding;
import com.antriansehat.application.interactor.DaftarAntrianInteractor;
import com.antriansehat.application.model.ScheduleOfHA;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.DaftarAntrianPresenter;
import com.antriansehat.application.presenter.ShowTicketPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DaftarAntrianActivity extends AppCompatActivity implements DaftarAntrianContract.View, View.OnClickListener,
        BaseAuthenticatedView, BottomNavigationView.OnNavigationItemSelectedListener{
    private ActivityDaftarAntrianBinding binding;
    private DaftarAntrianPresenter presenter;
    private boolean isFromCurrentUser = true;
    private ScheduleOfHA scheduleOfHA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaftarAntrianBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DaftarAntrianPresenter(this, new DaftarAntrianInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView() {
        scheduleOfHA = (ScheduleOfHA) getIntent().getExtras().get("schedule");
        binding.bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    @Override
    public void registerSuccess(String message) {

    }

    @Override
    public void registerFailed(String message) {

    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homePage = new Intent(DaftarAntrianActivity.this, HomeActivity.class);
                startActivity(homePage);
                break;
            case R.id.action_user:
                Intent profilePage = new Intent(DaftarAntrianActivity.this, ProfileActivity.class);
                startActivity(profilePage);
                break;
            case R.id.action_time:
                Intent historyPage = new Intent(DaftarAntrianActivity.this, RiwayatTiketActivity.class);
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
