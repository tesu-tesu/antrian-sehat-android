package com.antriansehat.application.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.MainContract;
import com.antriansehat.application.interactor.MainInteractor;
import com.antriansehat.application.presenter.MainPresenter;
import com.antriansehat.application.util.UtilProvider;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this, new MainInteractor(UtilProvider.getSharedPreferencesUtil()));
        presenter.checkIsUserLogin();
    }

    @Override
    public void whenUserLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), ListBookActivity.class));
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
