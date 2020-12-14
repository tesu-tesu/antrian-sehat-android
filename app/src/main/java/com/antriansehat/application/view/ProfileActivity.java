package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.databinding.ActivityProfilePageBinding;
import com.antriansehat.application.interactor.ProfileInteractor;
import com.antriansehat.application.presenter.ProfilePresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, View.OnClickListener
        , BottomNavigationView.OnNavigationItemSelectedListener, BaseAuthenticatedView {
    private ProfilePresenter presenter;
    private ActivityProfilePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProfilePresenter(this, new ProfileInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView() {
        binding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnLogout.getId()){
            onLogoutAction();
        }else if(v.getId() == binding.btnPengaturan.getId()){
            onProfileSetting();
        }
    }

    private void onProfileSetting() {
        Intent intent = new Intent(this, ProfileSettingActivity.class);
        startActivity(intent);
    }

    private void onLogoutAction() {
        presenter.logout();  //delete token
        Toast.makeText(getApplicationContext(), "You're logged out!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();   //finish all activities before this
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent profile = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(profile);
                this.finish();
                break;
            case R.id.action_user:
                break;
            case R.id.action_time:
                Intent time = new Intent(ProfileActivity.this,RiwayatTiketActivity.class);
                startActivity(time);
                this.finish();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }
}
