package com.mounacheikhna.challenge.ui.main.stops;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.StopPoint;
import java.util.ArrayList;
import java.util.List;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopPointViewHolder> {

    private List<StopPoint> stopPoints = new ArrayList<>();

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

    public void setItems(final List<StopPoint> stopPoints) {
        this.stopPoints = stopPoints;
        notifyDataSetChanged();
    }

    static class StopPointViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stop_name_tv) TextView stopName;

        private StopPoint stopPoint;

        StopPointViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        static int getLayoutId() {
            return R.layout.stop_point_item_layout;
        }

        void bind(final StopPoint data) {
            this.stopPoint = data;
            stopName.setText(data.commonName());
        }

        StopPoint getStopPoint() {
            return stopPoint;
        }
    }
}
