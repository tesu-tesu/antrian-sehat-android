package com.antriansehat.application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.databinding.TicketAkanDatangBinding;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public class RiwayatTiketAkanDatangAdapter extends RecyclerView.Adapter<RiwayatTiketAkanDatangAdapter.ViewHolder> {
    private List<WaitingList> waitingLists;
    private LayoutInflater layoutInflater;
    private ItemClickListener listener;

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
    public void onBindViewHolder(@NonNull RiwayatTiketAkanDatangAdapter.ViewHolder holder, final int position) {
        holder.binding.setWaitinglist(waitingLists.get(position));
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(waitingLists.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (waitingLists != null) {
            return waitingLists.size();
        } else {
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

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(WaitingList waitingList);
    }
}
