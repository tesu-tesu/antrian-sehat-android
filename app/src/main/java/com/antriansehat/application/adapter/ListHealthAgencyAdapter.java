package com.antriansehat.application.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.R;
import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.databinding.ItemPuskesmasBinding;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListHealthAgencyAdapter extends RecyclerView.Adapter<ListHealthAgencyAdapter.ViewHolder>{
    private List<HealthAgency> healthAgencies;
    private LayoutInflater layoutInflater;
    private ListHealthAgencyListener listHealthAgencyListener;

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
    public void onBindViewHolder(@NonNull ListHealthAgencyAdapter.ViewHolder holder, final int position) {
        holder.binding.setHealthAgency(healthAgencies.get(position));
        holder.binding.cardViewItemPuskesmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listHealthAgencyListener.onCardClick(healthAgencies.get(position));
            }
        });
        if(healthAgencies.get(position).getImage() != null) {
            System.out.println("PATH " + ApiConstant.SERVER_NAME + healthAgencies.get(position).getImage());
            Picasso.get()
                    .load(ApiConstant.SERVER_NAME + healthAgencies.get(position).getImage())
                    .fit()
                    .error(R.drawable.ic_puskesmas_icon)
                    .into(holder.binding.ivImageHealthAgency);
         }
    }

    public void setListHealthAgencyClickListener(ListHealthAgencyListener listHealthAgencyListener) {
        this.listHealthAgencyListener = listHealthAgencyListener;
    }

    public interface ListHealthAgencyListener {
        void onCardClick(HealthAgency healthAgency);
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
