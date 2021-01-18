package com.antriansehat.application.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.R;
import com.antriansehat.application.databinding.ItemScheduleBinding;
import com.antriansehat.application.model.HealthAgency;
import com.antriansehat.application.model.Schedule;

import java.util.List;

public class ListScheduleAdapter extends RecyclerView.Adapter<ListScheduleAdapter.ViewHolder>{
    private List<Schedule> schedules;
    private LayoutInflater layoutInflater;
    private ListScheduleListener listScheduleListener;
    private int checkedPosition = -1;

    public ListScheduleAdapter(List<Schedule> schedules, LayoutInflater layoutInflater) {
        this.schedules = schedules;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ListScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListScheduleAdapter.ViewHolder(ItemScheduleBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull final ListScheduleAdapter.ViewHolder holder, final int position) {
        holder.binding.setSchedule(schedules.get(position));
        holder.bind();
    }

    public void setListScheduleClickListener(ListScheduleListener listScheduleListener) {
        this.listScheduleListener = listScheduleListener;
        notifyDataSetChanged();
    }

    public Schedule getSelectedSchedule() {
        if(checkedPosition == -1)
            return null;
        return schedules.get(checkedPosition);
    }

    public interface ListScheduleListener {
        void onCardClick(Schedule schedule);
    }

    @Override
    public int getItemCount() {
        if(schedules != null){
            return schedules.size();
        }
        else {
            return 0;
        }
    }

//    public ItemScheduleBinding getItemBinding(){
//        return ItemScheduleBinding.inflate(layoutInflater);
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemScheduleBinding binding;

        void bind() {
            if (checkedPosition == -1) {
                binding.cardScheduleItem.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    binding.cardScheduleItem.setBackgroundResource(R.drawable.bt_blue_schedule);
                } else {
                    binding.cardScheduleItem.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.cardScheduleItem.setBackgroundResource(R.drawable.bt_blue_schedule);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }

        public ViewHolder(ItemScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
