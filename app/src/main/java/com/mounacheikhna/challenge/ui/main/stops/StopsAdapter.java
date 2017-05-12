package com.mounacheikhna.challenge.ui.main.stops;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.StopPoint;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

public class StopsAdapter extends RecyclerView.Adapter<StopPointViewHolder> {

    private List<CompleteStopPoint> stopPoints = new ArrayList<>();
    private StopPoint closestStopPoint;
    final PublishSubject<StopPoint> stopPointSave = PublishSubject.create();

    @Override
    public StopPointViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(StopPointViewHolder.getLayoutId(), parent, false);
        return new StopPointViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final StopPointViewHolder holder, final int position) {
        final CompleteStopPoint item = stopPoints.get(position);
        holder.bind(item, closestStopPoint != null && closestStopPoint.equals(item.stopPoint()),
            stopPointSave);
    }

    @Override
    public int getItemCount() {
        return stopPoints.size();
    }

    void setItems(final List<StopPoint> stopPoints) {
        this.stopPoints.clear();
        for (StopPoint stopPoint : stopPoints) {
            this.stopPoints.add(CompleteStopPoint.create(stopPoint, new ArrayList<>()));
        }
        notifyDataSetChanged();
    }

    void addItem(CompleteStopPoint result) {
        this.stopPoints.add(result);
        notifyDataSetChanged();
    }

    void clear() {
        this.stopPoints.clear();
        notifyDataSetChanged();
    }

}
