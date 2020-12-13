package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.databinding.ActivityProfileSettingBinding;
import com.antriansehat.application.interactor.ProfileSettingInteractor;
import com.antriansehat.application.presenter.ProfileSettingPresenter;
import com.antriansehat.application.util.UtilProvider;

public class ProfileSettingActivity extends AppCompatActivity implements ProfileSettingContract.View, View.OnClickListener{
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
            onSaveUpdateClick();
        }else if(v.getId() == binding.btnCancel.getId()){
            backToProfile();
        }
    }

    private void backToProfile() {
        this.finish();
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveUpdateClick() {
        binding.btnSave.setEnabled(false);
        presenter.updateProfile(binding.etNama.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etNIK.getText().toString());

    }

    @Override
    public void updateSuccess(String message) {
        binding.btnSave.setEnabled(true);
        makeToast(message);
        backToProfile();
    }

    @Override
    public void updateFailed(String message) {
        binding.btnSave.setEnabled(true);
        makeToast(message);
    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
