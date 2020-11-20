package com.antriansehat.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.R;
import com.antriansehat.application.databinding.ItemPuskesmasBinding;
import com.antriansehat.application.model.HealthAgency;

import java.util.List;

public class ListHealthAgencyAdapter extends RecyclerView.Adapter<ListHealthAgencyAdapter.ViewHolder>{
    private List<HealthAgency> healthAgencies;
    private LayoutInflater layoutInflater;

    public ListHealthAgencyAdapter(List<HealthAgency> healthAgencies, LayoutInflater layoutInflater) {
        this.healthAgencies = healthAgencies;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ListHealthAgencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListHealthAgencyAdapter.ViewHolder(ItemPuskesmasBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListHealthAgencyAdapter.ViewHolder holder, int position) {
        holder.binding.setHealthAgency(healthAgencies.get(position));
    }

    @Override
    public int getItemCount() {
        if(healthAgencies != null){
            return healthAgencies.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemPuskesmasBinding binding;

        public ViewHolder(ItemPuskesmasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
