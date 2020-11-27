package com.antriansehat.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.R;
import com.antriansehat.application.databinding.ItemPolyBinding;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Polyclinic;

import java.util.List;

public class ListPolyclinicAdapter extends RecyclerView.Adapter<ListPolyclinicAdapter.ViewHolder>{
    private List<Polyclinic> polyclinics;
    private LayoutInflater layoutInflater;
    private ListPolyclinicListener listPolyclinicListener;

    public ListPolyclinicAdapter(List<Polyclinic> polyclinics, LayoutInflater layoutInflater) {
        this.polyclinics = polyclinics;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ListPolyclinicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListPolyclinicAdapter.ViewHolder(ItemPolyBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListPolyclinicAdapter.ViewHolder holder, final int position) {
        holder.binding.setPolyclinic(polyclinics.get(position));
        holder.binding.cardViewItemPoly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPolyclinicListener.onCardClick(polyclinics.get(position));
            }
        });
    }

    public void setListPolyclinicClickListener(ListPolyclinicAdapter.ListPolyclinicListener listPolyclinicListener) {
        this.listPolyclinicListener = listPolyclinicListener;
    }

    public interface ListPolyclinicListener {
        void onCardClick(Polyclinic polyclinic);
    }

    @Override
    public int getItemCount() {
        if(polyclinics != null){
            return polyclinics.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemPolyBinding binding;

        public ViewHolder(ItemPolyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
