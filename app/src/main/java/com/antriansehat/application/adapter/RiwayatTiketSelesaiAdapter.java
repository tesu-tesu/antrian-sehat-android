package com.antriansehat.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.databinding.TicketHariIniBinding;
import com.antriansehat.application.databinding.TicketSelesaiBinding;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public class RiwayatTiketSelesaiAdapter extends RecyclerView.Adapter<RiwayatTiketSelesaiAdapter.ViewHolder> {
    private List<WaitingList> waitingLists;
    private LayoutInflater layoutInflater;

    public RiwayatTiketSelesaiAdapter(List<WaitingList> waitingLists, LayoutInflater layoutInflater) {
        this.waitingLists = waitingLists;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public RiwayatTiketSelesaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RiwayatTiketSelesaiAdapter.ViewHolder(TicketSelesaiBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTiketSelesaiAdapter.ViewHolder holder, int position) {
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
        TicketSelesaiBinding binding;

        public ViewHolder(TicketSelesaiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
