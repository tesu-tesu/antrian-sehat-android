package com.antriansehat.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.databinding.ItemResidenceNumberBinding;

import java.util.List;

public class ListBookedResidenceNumberAdapter extends RecyclerView.Adapter<ListBookedResidenceNumberAdapter.ViewHolder> {
    private List<String> residenceNumbers;
    private LayoutInflater layoutInflater;

    public ListBookedResidenceNumberAdapter(List<String> residenceNumbers, LayoutInflater layoutInflater) {
        this.residenceNumbers = residenceNumbers;
        this.layoutInflater = layoutInflater;
    }

    public ListBookedResidenceNumberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListBookedResidenceNumberAdapter.ViewHolder(ItemResidenceNumberBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListBookedResidenceNumberAdapter.ViewHolder holder, final int position) {
        holder.binding.setResidenceNumber(residenceNumbers.get(position));
    }

    @Override
    public int getItemCount() {
        if (residenceNumbers != null)
            return residenceNumbers.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemResidenceNumberBinding binding;

        public ViewHolder(@NonNull ItemResidenceNumberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

