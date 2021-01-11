package com.antriansehat.application.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.antriansehat.application.R;
import com.antriansehat.application.adapter.ViewPagerAdapter;
import com.antriansehat.application.databinding.ActivityRiwayatTiketBinding;
import com.antriansehat.application.view.fragment.TiketAkanDatangFragment;
import com.antriansehat.application.view.fragment.TiketHariIniFragment;
import com.antriansehat.application.view.fragment.TiketSelesaiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RiwayatTiketActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BaseAuthenticatedView, View.OnClickListener {
    private ActivityRiwayatTiketBinding binding;
    private TiketSelesaiFragment tiketSelesaiFragment;
    private TiketHariIniFragment tiketHariIniFragment;
    private TiketAkanDatangFragment tiketAkanDatangFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRiwayatTiketBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initView();

        tiketSelesaiFragment = new TiketSelesaiFragment();
        tiketHariIniFragment = new TiketHariIniFragment();
        tiketAkanDatangFragment = new TiketAkanDatangFragment();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        adapter.addFragment(tiketSelesaiFragment, "Selesai");
        adapter.addFragment(tiketHariIniFragment, "Hari Ini");
        adapter.addFragment(tiketAkanDatangFragment, "Akan Datang");

        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(1);
        binding.tabs.setupWithViewPager(binding.viewPager);
    }

    private void initView() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        //binding.btBack.setOnClickListener(this);
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
    public void onBackPressed() {
        super.onBackPressed();
        goToBackPage();
    }

    public void onClick(View v) {
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
}