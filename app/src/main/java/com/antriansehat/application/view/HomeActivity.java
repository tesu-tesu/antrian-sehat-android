package com.antriansehat.application.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.HomeContract;
import com.antriansehat.application.databinding.ActivityHomeBinding;
import com.antriansehat.application.interactor.HomeInteractor;
import com.antriansehat.application.presenter.HomePresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements HomeContract.View,
            BottomNavigationView.OnNavigationItemSelectedListener,
            BaseAuthenticatedView {
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
    }

    @Override
    public void whenUserLogin() {
        //loading data from API to show in home
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
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
}
