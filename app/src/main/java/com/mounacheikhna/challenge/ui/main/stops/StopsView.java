package com.mounacheikhna.challenge.ui.main.stops;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.ui.recyclerview.ClickItemTouchListener;
import com.mounacheikhna.challenge.ui.recyclerview.DividerItemDecoration;
import com.mounacheikhna.challenge.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.mounacheikhna.challenge.ui.stopdetails.StopDetailsActivity;
import java.util.List;

public class StopsView extends LinearLayout implements StopsScreen {

    @BindView(R.id.rv) RecyclerViewWithEmptyProgress stopsRv;
    @BindView(R.id.progress) LottieAnimationView progressView;
    @BindView(R.id.empty_container) ViewGroup emptyContainer;

    private StopsPresenter presenter;
    private StopsAdapter stopsAdapter;

    public StopsView(Context context) {
        this(context, null);
    }

    public StopsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StopsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view = LayoutInflater.from(context).inflate(R.layout.stops_view, this, true);

        ButterKnife.bind(this, view);
        setupStopsRv();
    }

    public void bind(StopsPresenter stopsPresenter) {
        this.presenter = stopsPresenter;
        this.presenter.bind(this);
    }

    private void setupStopsRv() {
        stopsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        stopsAdapter = new StopsAdapter();
        stopsRv.setAdapter(stopsAdapter);
        stopsRv.setEmptyView(emptyContainer);
        stopsRv.setProgress(progressView);

        //TODO: maybe instead use support lib DividerItemDecoration
        stopsRv.addItemDecoration(
            new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL,
                10, false));

        stopsRv.addOnItemTouchListener(new ClickItemTouchListener(stopsRv) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override
            protected boolean performItemClick(RecyclerView parent, View view, int position,
                long id) {
                StopDetailsActivity.start(getContext(), stopsAdapter.getItem(position));
                return true;
            }

            @Override
            public boolean performItemLongClick(RecyclerView parent, View view, int position,
                long id) {
                return false;
            }
        });
    }

    @Override
    public boolean hasLocationPermission() {
        return PermissionManager.hasLocation(getContext());
    }

    public void displayStopPoints(List<StopPoint> stopPoints) {
        stopsAdapter.setItems(stopPoints);
    }

    @Override
    public void displayError(Throwable throwable) {
        //TODO: handle the specific case of each error (network and other)
        Snackbar.make(stopsRv, R.string.error_fetch_stops, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayStopPoint(CompleteStopPoint stopPoint) {
        stopsAdapter.addItem(stopPoint);
    }

    @Override
    public void clearStops() {
        stopsAdapter.clear();
    }

    @Override
    public void onLocationDenied() {
        Snackbar.make(stopsRv, R.string.location_not_enabled, Snackbar.LENGTH_LONG)
            .setAction(R.string.enable_location, v -> presenter.requestLocationPermission())
            .show();
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.unbind();
        super.onDetachedFromWindow();
    }
}