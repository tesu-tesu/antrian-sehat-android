package com.antriansehat.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.databinding.TicketAkanDatangBinding;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public class RiwayatTiketAkanDatangAdapter extends RecyclerView.Adapter<RiwayatTiketAkanDatangAdapter.ViewHolder> {
    private List<WaitingList> waitingLists;
    private LayoutInflater layoutInflater;

    public RiwayatTiketAkanDatangAdapter(List<WaitingList> waitingLists, LayoutInflater layoutInflater) {
        this.waitingLists = waitingLists;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public RiwayatTiketAkanDatangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RiwayatTiketAkanDatangAdapter.ViewHolder(TicketAkanDatangBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTiketAkanDatangAdapter.ViewHolder holder, int position) {
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
        TicketAkanDatangBinding binding;

        public ViewHolder(TicketAkanDatangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
