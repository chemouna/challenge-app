package com.mounacheikhna.challenge.ui.main.stops;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.Departure;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.ui.recyclerview.ClickItemTouchListener;
import com.mounacheikhna.challenge.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.mounacheikhna.challenge.ui.stopdetails.StopDetailsActivity;
import io.reactivex.Observable;
import java.util.List;

public class StopsView extends LinearLayout implements StopsScreen {

    @BindView(R.id.rv) RecyclerViewWithEmptyProgress stopsRv;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.no_stops_tv) TextView noStopsTv;

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
        stopsRv.setEmptyView(noStopsTv);
        stopsRv.setProgress(progressBar);

        stopsRv.addOnItemTouchListener(new ClickItemTouchListener(stopsRv) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override
            protected boolean performItemClick(RecyclerView parent, View view, int position,
                long id) {
                StopDetailsActivity.start(stopsAdapter.getItem(position));
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

    @Override
    public Observable<Object> stopPointSelected() {
        return null;
    }

    @Override
    public void displayStopDetails(Object o) {

    }

    @Override
    public void showLoadingView(boolean b) {

    }

    @Override
    public void showNoStopsView(boolean b) {

    }

    /*public void displayStopPoints(List<StopPoint> stopPoints) {
        stopsAdapter.setItems(stopPoints);
    }*/

    @Override
    public void displayStopPoint(CompleteStopPoint result) {
        stopsAdapter.addItem(result);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.unbind();
        super.onDetachedFromWindow();
    }
}