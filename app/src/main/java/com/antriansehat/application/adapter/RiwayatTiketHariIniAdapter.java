package com.antriansehat.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.databinding.TicketAkanDatangBinding;
import com.antriansehat.application.databinding.TicketHariIniBinding;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public class RiwayatTiketHariIniAdapter extends RecyclerView.Adapter<RiwayatTiketHariIniAdapter.ViewHolder> {
    private List<WaitingList> waitingLists;
    private LayoutInflater layoutInflater;

    public RiwayatTiketHariIniAdapter(List<WaitingList> waitingLists, LayoutInflater layoutInflater) {
        this.waitingLists = waitingLists;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public RiwayatTiketHariIniAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RiwayatTiketHariIniAdapter.ViewHolder(TicketHariIniBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTiketHariIniAdapter.ViewHolder holder, int position) {
        holder.binding.setWaitinglist(waitingLists.get(position));
    }

    @Override
    public int getItemCount() {
        if(waitingLists != null){
            return waitingLists.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TicketHariIniBinding binding;

        public ViewHolder(TicketHariIniBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
