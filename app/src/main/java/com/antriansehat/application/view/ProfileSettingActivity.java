package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.contract.ProfileContract;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.databinding.ActivityProfilePageBinding;
import com.antriansehat.application.databinding.ActivityProfileSettingBinding;
import com.antriansehat.application.interactor.ProfileInteractor;
import com.antriansehat.application.interactor.ProfileSettingInteractor;
import com.antriansehat.application.presenter.ProfilePresenter;
import com.antriansehat.application.presenter.ProfileSettingPresenter;
import com.antriansehat.application.util.UtilProvider;

public class ProfileSettingActivity extends AppCompatActivity implements ProfileSettingContract.View, View.OnClickListener {
    private ProfileSettingPresenter presenter;
    private ActivityProfileSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProfileSettingPresenter(this, new ProfileSettingInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnSave.getId()){
            onSaveUpdate();
        }else if(v.getId() == binding.btnCancel.getId()){
            backToProfile();
        }
    }

    private void backToProfile() {
    }

    private void onSaveUpdate() {
    }

    @Override
    public void saveUpdate() {

    }
}
