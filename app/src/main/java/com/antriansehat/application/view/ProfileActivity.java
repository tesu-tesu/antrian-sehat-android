package com.antriansehat.application.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.R;
import com.antriansehat.application.databinding.ActivityHomeBinding;
import com.antriansehat.application.databinding.ActivityProfilePageBinding;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityProfilePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnLogout.getId()){
            onLogoutAction();
        }
    }

    private void onLogoutAction() {

    }
}
