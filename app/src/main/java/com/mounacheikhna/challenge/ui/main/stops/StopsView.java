package com.mounacheikhna.challenge.ui.main.stops;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.helpers.PermissionManager;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.mounacheikhna.challenge.ui.recyclerview.SimpleListDividerDecorator;
import java.util.List;
import rx.functions.Action1;

public class StopsView extends LinearLayout implements StopsScreen {

    @BindView(R.id.rv) RecyclerViewWithEmptyProgress stopsRv;
    @BindView(R.id.progress) LottieAnimationView progressView;
    @BindView(R.id.empty_container) ViewGroup emptyContainer;
    @BindDimen(R.dimen.divider_padding_start) float dividerPaddingStart;

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

        stopsRv.addItemDecoration(new SimpleListDividerDecorator(
            ContextCompat.getDrawable(getContext(), R.drawable.stops_divider), false));

        stopsAdapter.stopPointSave.subscribe(presenter::savePoint);
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