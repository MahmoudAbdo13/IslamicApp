package com.mahomud.islamicapp.ui.prayerTimes.prayerTimesHome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahomud.islamicapp.R;
import com.mahomud.islamicapp.data.pojo.PrayerTiming;


import java.util.List;

public class PrayerTimesAdapter extends RecyclerView.Adapter<PrayerTimesAdapter.ViewHolder> {

    private List<PrayerTiming> timing;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_prayer,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(timing.get(position));
    }

    @Override
    public int getItemCount() {
        return timing.size();
    }

    public void setTiming(List<PrayerTiming> timing) {
        this.timing = timing;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prayerName, prayerTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prayerName = itemView.findViewById(R.id.prayer_name);
            prayerTime = itemView.findViewById(R.id.prayer_time);

        }

        public void bind(PrayerTiming timing){
            prayerName.setText(timing.getParyerName());
            prayerTime.setText(timing.getPayerTime());
        }
    }
}
