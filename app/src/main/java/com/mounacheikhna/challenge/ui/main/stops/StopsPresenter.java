package com.mounacheikhna.challenge.ui.main.stops;

import android.support.annotation.NonNull;
import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.data.GoogleApiClientProvider;
import com.mounacheikhna.challenge.data.LocationRequester;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.model.LatLng;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class StopsPresenter {

    static final double DEFAULT_LATITUDE = 51.5033;
    static final double DEFAULT_LONGITUDE = 0.1195;

    private final GoogleApiClientProvider googleApiClientWrapper;
    private final LocationRequester locationRequester;
    private final TflApi tflApi;

    @Inject
    public StopsPresenter(GoogleApiClientProvider googleApiClientProvider,
        LocationRequester locationRequester, PermissionManager permissionManager, TflApi tflApi) {
        this.googleApiClientWrapper = googleApiClientProvider;
        this.locationRequester = locationRequester;
        this.tflApi = tflApi;
    }

    public void bind(StopsScreen screen) {
        if (screen.hasLocationPermission()) {
            getLocationAndUpdateStops(screen);
        } else {
            screen.requestLocationPermission();
        }

        screen.stopPointSelected()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(screen::displayStopDetails);
    }

    private void getLocationAndUpdateStops(StopsScreen screen) {
        googleApiClientWrapper.registerConnectionCallbacks(() -> {
            LatLng latLng = locationRequester.lastLocation(googleApiClientWrapper);

            if (latLng == null || !isInLondon(latLng)) {
                latLng = LatLng.create(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
            }
            screen.showLoadingView(true);

            tflApi.stopPoint(latLng.latitude(), latLng.longitude())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(stopPoints -> {
                    screen.showLoadingView(false);
                    if (stopPoints.size() == 0) {
                        screen.showNoStopsView(true);
                    } else {
                        screen.setStopPoints(stopPoints);
                    }
                }, Throwable::printStackTrace);
        }); googleApiClientWrapper.connect();
    }

    private boolean isInLondon(@NonNull final LatLng latLng) {
        return (latLng.latitude() > 51.288923
            && latLng.latitude() < 51.706130
            && latLng.longitude() > -0.524597
            && latLng.longitude() < 0.280151);
    }

    void unbind() {
        googleApiClientWrapper.disconnect();
    }
}
