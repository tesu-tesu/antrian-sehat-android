package com.antriansehat.application.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.contract.RegisterContract;
import com.antriansehat.application.databinding.ActivityRegisterBinding;
import com.antriansehat.application.interactor.RegisterInteractor;
import com.antriansehat.application.presenter.RegisterPresenter;
import com.antriansehat.application.util.UtilProvider;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener{
    private RegisterContract.Presenter presenter;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RegisterPresenter(this, new RegisterInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.btBack.setOnClickListener(this);
        binding.btRegister.setOnClickListener(this);
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
    public void registerSuccess() {

    }

    @Override
    public void registerFailed(String message) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btRegister.getId()){
            onButtonRegisterClick();
        }
    }

    public void onButtonRegisterClick(){
        presenter.register(
                binding.etName.getText().toString(),
                binding.etPhone.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etPassword.getText().toString(),
                binding.etConfirmPassword.getText().toString()
        );
    }
}
