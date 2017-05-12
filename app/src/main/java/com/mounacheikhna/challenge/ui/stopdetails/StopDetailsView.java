package com.mounacheikhna.challenge.ui.stopdetails;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.ui.recyclerview.RecyclerViewWithEmptyProgress;
import java.util.List;

public class StopDetailsView extends LinearLayout implements StopDetailsScreen {

    @BindView(R.id.rv) RecyclerViewWithEmptyProgress rv;
    @BindView(R.id.empty_tv) TextView emptyTv;
    @BindView(R.id.progress) LottieAnimationView progressView;

    private StopDetailsAdapter stopDetailsAdapter;

    public StopDetailsView(Context context) {
        this(context, null);
    }

    public StopDetailsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StopDetailsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view =
            LayoutInflater.from(context).inflate(R.layout.stop_details_view, this, true);
        ButterKnife.bind(this, view);
        setupDetailsRv();
    }

    private void setupDetailsRv() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        stopDetailsAdapter = new StopDetailsAdapter();
        rv.setAdapter(stopDetailsAdapter);
        rv.setEmptyView(emptyTv);
        rv.setProgress(progressView);
    }

    public void bind(StopDetailsPresenter presenter, CompleteStopPoint completeStopPoint) {
        presenter.bind(this, completeStopPoint);
    }

    @Override
    public void displayStops(List<StopPoint> stopPoints) {
        stopDetailsAdapter.setItems(stopPoints);
    }

    @Override
    public void selectLocationStop(LatLng latLng) {
        stopDetailsAdapter.selectNear(latLng);
    }
}
