package com.mounacheikhna.challenge.ui.savedstops;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mounacheikhna.challenge.model.SavedStop;
import java.util.ArrayList;
import java.util.List;

public class SavedStopsAdapter extends RecyclerView.Adapter<SavedStopViewHolder> {

    private List<SavedStop> savedStops = new ArrayList<>();

    @Override
    public SavedStopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(SavedStopViewHolder.getLayoutId(), parent, false);
        return new SavedStopViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(SavedStopViewHolder holder, int position) {
        final SavedStop item = savedStops.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return savedStops.size();
    }

    public void setSavedStops(List<SavedStop> items) {
        this.savedStops = items;
        notifyDataSetChanged();
    }
}
