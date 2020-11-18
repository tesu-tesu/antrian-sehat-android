package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.databinding.ActivityProfilePageBinding;
import com.antriansehat.application.interactor.ProfileInteractor;
import com.antriansehat.application.presenter.ProfilePresenter;
import com.antriansehat.application.util.UtilProvider;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, View.OnClickListener {
    private ProfilePresenter presenter;
    private ActivityProfilePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProfilePresenter(this, new ProfileInteractor(UtilProvider.getSharedPreferencesUtil()));
        presenter.checkIsUserLogin();

        initView();
    }

    private void initView() {
        binding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void whenUserLogin() {
        //loading data from API to show in profile page
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnLogout.getId()){
            onLogoutAction();
        }
    }

    private void onLogoutAction() {
        presenter.logout();  //delete token
        Toast.makeText(getApplicationContext(), "You're logged out!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();   //finish all activities before this
    }
}
