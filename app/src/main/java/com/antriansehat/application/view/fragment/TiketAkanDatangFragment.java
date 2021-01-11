package com.antriansehat.application.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antriansehat.application.adapter.RiwayatTiketAkanDatangAdapter;
import com.antriansehat.application.contract.RiwayatTiketContract;
import com.antriansehat.application.databinding.FragmentTiketAkanDatangBinding;
import com.antriansehat.application.interactor.RiwayatTiketInteractor;
import com.antriansehat.application.model.WaitingList;
import com.antriansehat.application.presenter.RiwayatTiketPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.antriansehat.application.view.ShowTicketActivity;

import java.util.List;

public class TiketAkanDatangFragment extends Fragment implements RiwayatTiketContract.View {
    private FragmentTiketAkanDatangBinding binding;
    private RiwayatTiketContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentTiketAkanDatangBinding.inflate(getLayoutInflater());
        presenter = new RiwayatTiketPresenter(this, new RiwayatTiketInteractor(UtilProvider.getSharedPreferencesUtil()));

        binding.rvWaitingList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding.tvNoTicket.setVisibility(View.GONE);
        binding.rvWaitingList.setVisibility(View.GONE);
        presenter.requestTicket(RiwayatTiketContract.FUTURE_TICKET);
        return binding.getRoot();
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvWaitingList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWaitingList(List<WaitingList> waitingLists) {
        binding.rvWaitingList.setAdapter(new RiwayatTiketAkanDatangAdapter(waitingLists, getLayoutInflater()));

        if(waitingLists.size() == 0) {
            binding.tvNoTicket.setVisibility(View.VISIBLE);
            return;
        }
        ((RiwayatTiketAkanDatangAdapter) binding.rvWaitingList.getAdapter()).setOnItemClickListener(new RiwayatTiketAkanDatangAdapter.ItemClickListener() {
            @Override
            public void onItemClick(WaitingList waitingList) {
                showTicket(waitingList);
            }
        });
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        binding.tvNoTicket.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTicket(WaitingList waitingList) {
        Intent intent = new Intent(getContext(), ShowTicketActivity.class);
        intent.putExtra("waitinglist", waitingList);
        startActivity(intent);
    }
}
