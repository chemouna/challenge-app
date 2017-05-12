package com.mounacheikhna.challenge.ui.stopdetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.helpers.LocationHelper;
import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.ui.custom.LineStopPointView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StopDetailsAdapter
    extends RecyclerView.Adapter<StopDetailsAdapter.StopDetailsViewHolder> {

    private List<StopPoint> stopPoints = new ArrayList<>();
    private StopPoint closestStopPoint;

    @Override
    public StopDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(StopDetailsViewHolder.getLayoutId(), parent, false);
        return new StopDetailsViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(StopDetailsViewHolder holder, int position) {
        holder.bind(stopPoints.get(position), closestStopPoint);
    }

    @Override
    public int getItemCount() {
        return stopPoints.size();
    }

    void setItems(List<StopPoint> items) {
        this.stopPoints = items;
        notifyDataSetChanged();
    }

    void selectNear(LatLng latLng) {
        // TODO: this entire logic should instead be in the presenter and be done by it
        //TODO: just use the distance value in model StopPoint
        Map<Double, StopPoint> distances = new HashMap<>(this.stopPoints.size());
        for (int i = 0; i < this.stopPoints.size(); i++) {
            final StopPoint stopPoint = this.stopPoints.get(i);
            distances.put(
                LocationHelper.distance(latLng.latitude(), latLng.longitude(), stopPoint.lat(),
                    stopPoint.lon()), stopPoint);
        }
        final Double minDistance = Collections.min(distances.keySet());
        this.closestStopPoint = distances.get(minDistance);

        Collections.sort(stopPoints, (o1, o2) -> {
            final double distanceStop1 =
                LocationHelper.distance(latLng.latitude(), latLng.longitude(), o1.lat(),
                    o1.lon());
            final double distanceStop2 =
                LocationHelper.distance(latLng.latitude(), latLng.longitude(), o2.lat(),
                    o2 .lon());
            if (distanceStop1 == distanceStop2) return 0;
            return distanceStop1 < distanceStop2 ? -1 : 1;
        });

        notifyDataSetChanged();
    }

    static class StopDetailsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stop_view) LineStopPointView stopView;

        StopDetailsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        static int getLayoutId() {
            return R.layout.stop_details_item_layout;
        }

        void bind(StopPoint stopPoint, StopPoint nearestStopPoint) {
            stopView.setTitle(stopPoint.commonName());
            if(nearestStopPoint != null && stopPoint.id().equals(nearestStopPoint.id())) {
                stopView.selectedStopPoint(true);
            }
            else {
                stopView.selectedStopPoint(false);
            }
        }
    }
}
