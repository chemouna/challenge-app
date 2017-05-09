package com.mounacheikhna.challenge.ui.main.stops;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.Arrival;
import com.mounacheikhna.challenge.model.StopPoint;
import java.util.ArrayList;
import java.util.List;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopPointViewHolder> {

    private List<CompleteStopPoint> stopPoints = new ArrayList<>();

    @Override
    public StopPointViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(StopPointViewHolder.getLayoutId(), parent, false);
        return new StopPointViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final StopPointViewHolder holder, final int position) {
        holder.bind(stopPoints.get(position));
    }

    @Override
    public int getItemCount() {
        return stopPoints.size();
    }

    /*void setItems(final List<CompleteStopPoint> stopPoints) {
        this.stopPoints = stopPoints;
        notifyDataSetChanged();
    }*/

    public void setItems(final List<StopPoint> stopPoints) {
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

    CompleteStopPoint getItem(int position) {
        return this.stopPoints.get(position);
    }

    void clear() {
        this.stopPoints.clear();
        notifyDataSetChanged();
    }

    static class StopPointViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stop_name_tv) TextView stopNameTv;
        @BindView(R.id.departures_tv) TextView departuresTv;

        private CompleteStopPoint completeStopPoint;

        StopPointViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        static int getLayoutId() {
            return R.layout.stop_point_item_layout;
        }

        void bind(final CompleteStopPoint item) {
            this.completeStopPoint = item;
            stopNameTv.setText(item.stopPoint().commonName());
            StringBuilder formattedDepartures = new StringBuilder();
            for (Arrival arrival : item.departures()) {
                formattedDepartures.append(arrival.timeToStation()).append(" ");
            }
            departuresTv.setText(formattedDepartures.toString());
        }

        CompleteStopPoint getCompleteStopPoint() {
            return completeStopPoint;
        }
    }
}
