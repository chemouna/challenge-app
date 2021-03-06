package com.mounacheikhna.challenge.ui.stopdetails;

import android.util.Log;
import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.helpers.GoogleApiClientProvider;
import com.mounacheikhna.challenge.helpers.LocationRequester;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.model.Line;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import javax.inject.Inject;

public class StopDetailsPresenter {

    private final GoogleApiClientProvider googleApiClientProvider;
    private final TflApi api;
    private final LocationRequester locationRequester;

    @Inject
    public StopDetailsPresenter(GoogleApiClientProvider googleApiClientProvider, TflApi api,
        LocationRequester locationRequester) {
        this.googleApiClientProvider = googleApiClientProvider;
        this.api = api;
        this.locationRequester = locationRequester;
    }

    void bind(StopDetailsScreen screen, CompleteStopPoint completeStopPoint) {
        googleApiClientProvider.registerConnectionCallbacks(() -> {
            LatLng latLng = locationRequester.lastLocation(googleApiClientProvider);
            latLng = LatLng.create(51.5033, -0.1195); //this is for testing only (to get a value close to a station)
            screen.selectLocationStop(latLng);
        });
        fetchLineStops(screen, completeStopPoint);
    }

    private void fetchLineStops(StopDetailsScreen screen, CompleteStopPoint completeStopPoint) {
        List<Line> lines = completeStopPoint.stopPoint().lines();
        if (lines.isEmpty()) return;
        final Line line = lines.get(0);
        api.lineStops(line.id())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(stopPoints -> {
                screen.displayStops(stopPoints);
                googleApiClientProvider.connect();
            }, throwable -> Log.d("TEST", "accept: throwable : "+ throwable));
    }
}
