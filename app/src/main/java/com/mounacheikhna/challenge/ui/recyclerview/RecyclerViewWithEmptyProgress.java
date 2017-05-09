package com.mounacheikhna.challenge.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Simple RecyclerView subclass that supports providing an empty view (which
 * is displayed when the adapter has no data and hidden otherwise).
 */
public class RecyclerViewWithEmptyProgress extends RecyclerView {
    private View emptyView;
    private int numberHeaderFooter;
    private ProgressBar progress;
    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateEmptyViewProgress();
        }
    };

    public RecyclerViewWithEmptyProgress(Context context) {
        this(context, null);
    }

    public RecyclerViewWithEmptyProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewWithEmptyProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        numberHeaderFooter = 0;
    }

    public void setNumberHeadersFooters(int numberHeaderFooter) {
        this.numberHeaderFooter = numberHeaderFooter;
    }

    /**
     * Designate a view as the empty view. When the backing adapter has no
     * data this view will be made visible and the recycler view hidden.
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterAdapterDataObserver(dataObserver);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(dataObserver);
        }
        super.setAdapter(adapter);
        updateEmptyViewProgress();
    }

    private void updateEmptyViewProgress() {
        if (emptyView != null && getAdapter() != null) {
            boolean showEmptyView = getAdapter().getItemCount() == numberHeaderFooter;
            emptyView.setVisibility(showEmptyView ? VISIBLE : GONE);
            setVisibility(showEmptyView ? GONE : VISIBLE);
            progress.setVisibility(View.GONE);
            if (!showEmptyView) {
                setVisibility(VISIBLE);
            }
        }
    }

    public ProgressBar getProgress() {
        return progress;
    }

    public void setProgress(ProgressBar progress) {
        this.progress = progress;
    }
}

