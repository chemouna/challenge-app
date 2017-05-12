package com.mounacheikhna.challenge.ui.savedstops;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.SavedStop;
import com.mounacheikhna.challenge.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.mounacheikhna.challenge.ui.recyclerview.SimpleListDividerDecorator;
import java.util.List;

public class SavedStopsListView extends LinearLayout implements SavedStopsScreen {

    private SavedStopsPresenter savedStopsPresenter;

    @BindView(R.id.rv) RecyclerViewWithEmptyProgress stopsRv;
    @BindView(R.id.progress) LottieAnimationView progressView;
    @BindView(R.id.empty_container) ViewGroup emptyContainer;
    private SavedStopsAdapter savedStopsAdapter;

    public SavedStopsListView(Context context) {
        this(context, null);
    }

    public SavedStopsListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SavedStopsListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final View view =
            LayoutInflater.from(context).inflate(R.layout.saved_stops_view, this, true);

        ButterKnife.bind(this, view);
        setupSavedStopsList();
    }

    private void setupSavedStopsList() {
        stopsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        savedStopsAdapter = new SavedStopsAdapter();
        stopsRv.setAdapter(savedStopsAdapter);
        stopsRv.setEmptyView(emptyContainer);
        stopsRv.setProgress(progressView);

        stopsRv.addItemDecoration(new SimpleListDividerDecorator(
            ContextCompat.getDrawable(getContext(), R.drawable.stops_divider), false));
    }

    public void bind(SavedStopsPresenter savedStopsPresenter) {
        this.savedStopsPresenter = savedStopsPresenter;
        this.savedStopsPresenter.bind(this);
    }

    @Override
    public void display(List<SavedStop> savedStops) {
        savedStopsAdapter.setSavedStops(savedStops);
    }
}
