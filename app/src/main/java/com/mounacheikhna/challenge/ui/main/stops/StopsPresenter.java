package com.mounacheikhna.challenge.ui.main.stops;

import android.support.annotation.NonNull;
import android.util.Log;
import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.data.GoogleApiClientProvider;
import com.mounacheikhna.challenge.data.LocationRequester;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.model.StopPointResponse;
import com.mounacheikhna.challenge.ui.main.PermissionRequester;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class StopsPresenter {

    private static final double DEFAULT_LATITUDE = 51.5033;
    private static final double DEFAULT_LONGITUDE = -0.1195;
    private static final int STOP_POINTS_RADIUS = 900;

    private final GoogleApiClientProvider googleApiClientProvider;
    private final LocationRequester locationRequester;
    private final TflApi tflApi;
    private final PermissionRequester permissionRequester;
    private final PermissionManager permissionManager;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public StopsPresenter(GoogleApiClientProvider googleApiClientProvider,
        LocationRequester locationRequester, TflApi tflApi, PermissionRequester permissionRequester,
        PermissionManager permissionManager) {
        this.googleApiClientProvider = googleApiClientProvider;
        this.locationRequester = locationRequester;
        this.tflApi = tflApi;
        this.permissionRequester = permissionRequester;
        this.permissionManager = permissionManager;
    }

    void bind(StopsScreen screen) {
        if (screen.hasLocationPermission()) {
            getLocationAndUpdateStops(screen);
        } else {
            permissionManager.anyGanted(PermissionManager.REQUEST_LOCATION_PERMISSIONS)
                .subscribe(granted -> getLocationAndUpdateStops(screen));
            permissionManager.denials().subscribe(s -> {
                screen.onLocationDenied();
            });
            permissionRequester.requestLocation();
        }
    }

    private void getLocationAndUpdateStops(StopsScreen screen) {
        googleApiClientProvider.registerConnectionCallbacks(() -> {
            LatLng latLng = locationRequester.lastLocation(googleApiClientProvider);

            if (latLng == null || !isInsideLondon(latLng)) {
                latLng = LatLng.create(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
            }

            final Observable<StopPointResponse> stopPointObservable =
                tflApi.stopPoint(latLng.latitude(), latLng.longitude(), STOP_POINTS_RADIUS).cache();

            final Observable<CompleteStopPoint> stopPointsWithArrivalsObservable =
                stopPointObservable.flatMap(
                    response -> Observable.fromIterable(response.stopPoints())
                        .flatMap(stopPoint -> tflApi.arrivals(stopPoint.id())
                            .map(arrivals -> CompleteStopPoint.create(stopPoint, arrivals))));

            Observable.interval(0, 30, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(l -> screen.clearStops())
                .flatMap(l -> stopPointsWithArrivalsObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(screen::displayStopPoint,
                    throwable -> Log.d("TEST", "Error : " + throwable));
        });
        googleApiClientProvider.connect();
    }

    private boolean isInsideLondon(@NonNull final LatLng latLng) {
        return (latLng.latitude() > 51.288923
            && latLng.latitude() < 51.706130
            && latLng.longitude() > -0.524597
            && latLng.longitude() < 0.280151);
    }

    void unbind() {
        googleApiClientProvider.disconnect();
        compositeDisposable.clear();
    }

    public void requestLocationPermission() {
        permissionRequester.requestLocation();
    }
}
