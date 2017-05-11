package com.mounacheikhna.challenge.ui.main.stops;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.helpers.LocationHelper;
import com.mounacheikhna.challenge.model.Arrival;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.model.StopPoint;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopPointViewHolder> {

    private List<CompleteStopPoint> stopPoints = new ArrayList<>();
    private StopPoint closestStopPoint;

    @Override
    public StopPointViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(StopPointViewHolder.getLayoutId(), parent, false);
        return new StopPointViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final StopPointViewHolder holder, final int position) {
        final CompleteStopPoint item = stopPoints.get(position);
        holder.bind(item, closestStopPoint != null && closestStopPoint.equals(item.stopPoint()));
    }

    @Override
    public int getItemCount() {
        return stopPoints.size();
    }

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

    public void select(LatLng latLng) {
        // TODO: this entire logic should instead be in the presenter and be done by it

        //TODO: just use the distance value in model StopPoint
        Map<Double, StopPoint> distances = new HashMap<>(this.stopPoints.size());
        for (int i = 0; i < this.stopPoints.size(); i++) {
            final StopPoint stopPoint = this.stopPoints.get(i).stopPoint();
            distances.put(
                LocationHelper.distance(latLng.latitude(), latLng.longitude(), stopPoint.lat(),
                    stopPoint.lon()), stopPoint);
        }
        final Double minDistance = Collections.min(distances.keySet());
        this.closestStopPoint = distances.get(minDistance);

        Collections.sort(stopPoints, (o1, o2) -> {
            final double distanceStop1 =
                LocationHelper.distance(latLng.latitude(), latLng.longitude(), o1.stopPoint().lat(),
                    o1.stopPoint().lon());
            final double distanceStop2 =
                LocationHelper.distance(latLng.latitude(), latLng.longitude(), o2.stopPoint().lat(),
                    o2.stopPoint().lon());
            if (distanceStop1 == distanceStop2) return 0;
            return distanceStop1 < distanceStop2 ? -1 : 1;
        });

        notifyDataSetChanged();
    }

    static class StopPointViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stop_name_tv) TextView stopNameTv;
        @BindView(R.id.departures_tv) TextView arrivalsTv;
        @BindView(R.id.closest_indicator) TextView closestIndicator;
        @BindView(R.id.distance_to_tv) TextView distanceToTv;

        StopPointViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        static int getLayoutId() {
            return R.layout.stop_point_item_layout;
        }

        void bind(final CompleteStopPoint item, boolean closeToLocation) {
            stopNameTv.setText(item.stopPoint().commonName());
            /*StringBuilder formattedDepartures = new StringBuilder();
            for (Arrival arrival : item.arrivals()) {
                formattedDepartures.append(arrival.timeToStation()).append(" ");
            }
            arrivalsTv.setText(formattedDepartures.toString());*/
            if (!item.arrivals().isEmpty()) {
                final Arrival arrival = item.arrivals().get(0);
                final String timeDifferenceWithNow =
                    getTimeDifferenceWithNow(arrival.timeToStation());
                //arrival.timeToStation();
                arrivalsTv.setText(timeDifferenceWithNow);
            }
            closestIndicator.setVisibility(closeToLocation ? View.VISIBLE : View.GONE);

            //TODO: this is probably made better in terms of time to get there
            distanceToTv.setText(new DecimalFormat("##").format(item.stopPoint().distance()));
        }

        //TODO: use Clock and put this in a place where it can be tested
        String getTimeDifferenceWithNow(long time) {
            DateTime dateTime = new DateTime(time);

            Duration duration = new Duration(dateTime, DateTime.now());
            Period period = duration.toPeriod();

            //TODO: handle case where difference is days and hours
            StringBuilder timeStrBuilder = new StringBuilder();

            if (duration.getStandardHours() > 0) {
                timeStrBuilder.append(period.getHours()).append("h");
            }
            timeStrBuilder.append(new DecimalFormat("##").format(duration.getStandardMinutes()))
                .append("min");
            return timeStrBuilder.toString();
        }
    }
}
