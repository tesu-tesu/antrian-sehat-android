package com.antriansehat.application.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.R;
import com.antriansehat.application.contract.DaftarAntrianContract;
import com.antriansehat.application.databinding.ActivityDaftarAntrianBinding;
import com.antriansehat.application.interactor.DaftarAntrianInteractor;
import com.antriansehat.application.model.ScheduleOfHA;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.model.WaitingListFromSchedule;
import com.antriansehat.application.presenter.DaftarAntrianPresenter;
import com.antriansehat.application.presenter.ShowTicketPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DaftarAntrianActivity extends AppCompatActivity implements DaftarAntrianContract.View, View.OnClickListener,
        BaseAuthenticatedView, BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener{
    private ActivityDaftarAntrianBinding binding;
    private DaftarAntrianPresenter presenter;
    private String idSchedule;
    private Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaftarAntrianBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DaftarAntrianPresenter(this, new DaftarAntrianInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.spinner.setOnItemSelectedListener(this);

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pilihan_register,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        binding.spinner.setAdapter(adapter);
        binding.cardKeteranganPendaftaran.setVisibility(View.GONE);
        presenter.showWaitingList(idSchedule, date);

        binding.btBack.setOnClickListener(this);
        binding.btDaftarAntrian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btBack.getId()){
            finish();
        } else if(view.getId() == binding.btDaftarAntrian.getId()) {
            registerNewWaitingList();
        }
    }

    private void registerNewWaitingList() {
        String residenceNumber = binding.etNIK.getText().toString();
        presenter.register(idSchedule, date, residenceNumber);
    }

    @Override
    public void startLoading() {
//        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showWaitingList(WaitingListFromSchedule waitingList) {
        binding.setWaitingList(waitingList);
        binding.tvDate.setText(new SimpleDateFormat(" EEEE, dd MMMM yyyy").format(date));
        binding.cardKeteranganPendaftaran.setVisibility(View.VISIBLE);
    }

    @Override
    public void setResidenceNumber(String residenceNumber) {
        if(residenceNumber != null || !residenceNumber.equals("")) {
            binding.etNIK.setText(residenceNumber);
            binding.etNIK.setEnabled(false);
        }
    }

    @Override
    public void registerSuccess(String message) {
        Intent home = new Intent(DaftarAntrianActivity.this, HomeActivity.class);
        finish();
        startActivity(home);
        makeToast(message);
    }

    @Override
    public void registerFailed(String message) {
        makeToast(message);
    }

    @Override
    public void showError(String errorMessage) {
        makeToast(errorMessage);
    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homePage = new Intent(DaftarAntrianActivity.this, HomeActivity.class);
                startActivity(homePage);
                break;
            case R.id.action_user:
                Intent profilePage = new Intent(DaftarAntrianActivity.this, ProfileActivity.class);
                startActivity(profilePage);
                break;
            case R.id.action_time:
                Intent historyPage = new Intent(DaftarAntrianActivity.this, RiwayatTiketActivity.class);
                startActivity(historyPage);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        if(spinnerLabel.equalsIgnoreCase("Diri Sendiri")) {
            presenter.getResidenceNumber();
        } else {
            binding.etNIK.setEnabled(true);
            binding.etNIK.setText("");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.setAutofillHints("Pilih Pendaftar");
    }
}
