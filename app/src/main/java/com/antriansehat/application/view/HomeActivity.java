package com.antriansehat.application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.HomeContract;
import com.antriansehat.application.databinding.ActivityHomeBinding;
import com.antriansehat.application.databinding.ActivityListBookBinding;
import com.antriansehat.application.interactor.HomeInteractor;
import com.antriansehat.application.interactor.ListBookInteractor;
import com.antriansehat.application.model.Article;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.HomePresenter;
import com.antriansehat.application.presenter.ListBookPresenter;
import com.antriansehat.application.util.UtilProvider;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, View.OnClickListener {
    private ActivityHomeBinding binding;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
        presenter.checkIsUserLogin();
        presenter.requestNearestWaitingList();
    }

    private void initView() {
        binding.btCreateWL.setOnClickListener(this);
        binding.btListHA.setOnClickListener(this);
        binding.btListPoly.setOnClickListener(this);
        binding.btShowTicket.setOnClickListener(this);
        binding.cNearestWaitingList.setOnClickListener(this);

/*        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.logoutButton.setOnClickListener(this);*/
    }

    @Override
    public void whenUserLogin() {
        presenter.requestNearestWaitingList();
//        finish();
//        startActivity(new Intent(getApplicationContext(), ListBookActivity.class));
        //loading data from API to show in home
    }

    @Override
    public void whenUserNotLogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void showNearestWaitingList(WaitingList waitingList) {

    }

    @Override
    public void showListArticle(List<Article> articles) {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.cNearestWaitingList.getId()){
            onCardNearestWaitingList();
        }else if(view.getId() == binding.btShowTicket.getId()){
            onButtonShowTicket();
        }else if(view.getId() == binding.btListPoly.getId()){
            onButtonListPoly();
        }else if(view.getId() == binding.btListHA.getId()){
            onButtonListHA();
        }else if(view.getId() == binding.btCreateWL.getId()){
            onButtonCreateWaitingList();
        }
    }

    public void onCardNearestWaitingList() {

    }

    public void onButtonShowTicket() {

    }

    public void onButtonListPoly() {

    }

    public void onButtonListHA() {

    }

    public void onButtonCreateWaitingList() {

    }
}
