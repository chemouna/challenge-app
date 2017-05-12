package com.mounacheikhna.challenge.ui.savedstops;

import android.util.Log;
import com.mounacheikhna.challenge.annotation.ObserveOnScheduler;
import com.mounacheikhna.challenge.data.SavedStopManager;
import io.reactivex.Scheduler;
import javax.inject.Inject;

public class SavedStopsPresenter {

    private static final String TAG = "SavedStopsPresenter";

    private final SavedStopManager savedStopManager;
    private final Scheduler observeOnScheduler;

    @Inject
    public SavedStopsPresenter(SavedStopManager savedStopManager,
        @ObserveOnScheduler Scheduler observeOnScheduler) {
        this.savedStopManager = savedStopManager;
        this.observeOnScheduler = observeOnScheduler;
    }

    void bind(SavedStopsScreen screen) {
        savedStopManager.getStops()
            .observeOn(observeOnScheduler)
            .subscribe(screen::display, throwable -> Log.d(TAG, "accept: Error : " + throwable));
    }
}
