package com.antriansehat.application.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.databinding.ActivityDaftarAntrianBinding;
import com.antriansehat.application.interactor.DaftarAntrianInteractor;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.DaftarAntrianPresenter;
import com.antriansehat.application.presenter.ShowTicketPresenter;
import com.antriansehat.application.util.UtilProvider;

public class DaftarAntrianActivity extends AppCompatActivity implements DaftarAntrianContract.View, View.OnClickListener {
    private ActivityDaftarAntrianBinding binding;
    private DaftarAntrianPresenter presenter;
    private WaitingList waitingList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaftarAntrianBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DaftarAntrianPresenter(this, new DaftarAntrianInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();

    }

    private void initView() {

        binding.btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

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
}
