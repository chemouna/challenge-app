package com.mounacheikhna.challenge.ui.main.stops;

import android.support.annotation.NonNull;
import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.data.GoogleApiClientProvider;
import com.mounacheikhna.challenge.data.LocationRequester;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.ui.main.PermissionRequester;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class StopsPresenter {

    private static final double DEFAULT_LATITUDE = 51.5033;
    private static final double DEFAULT_LONGITUDE = -0.1195;

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
            //permissionManager.denials().subscribe(s -> permissionRequester.requestLocation());
            permissionRequester.requestLocation();
        }
    }

    private void getLocationAndUpdateStops(StopsScreen screen) {
        googleApiClientProvider.registerConnectionCallbacks(() -> {
            LatLng latLng = locationRequester.lastLocation(googleApiClientProvider);

            if (latLng == null || !isInLondon(latLng)) {
                latLng = LatLng.create(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
            }

            fetchStopPoints(screen, latLng);

            /*final Observable<StopPointResponse> stopPointObservable =
                tflApi.stopPoint(latLng.latitude(), latLng.longitude()).share();
            compositeDisposable.add(
                Observable.interval(0, 30, TimeUnit.SECONDS, Schedulers.io())
                .startWith(0L)
                .take(10)
                .flatMap(l -> stopPointObservable)
                .flatMap(response -> Observable.fromIterable(response.stopPoints())
                    .flatMap(stopPoint -> tflApi.arrivals(stopPoint.id())
                        .map(departures -> CompleteStopPoint.create(stopPoint, departures)))
                    .startWith(result -> StopsFetchUiModel.refresh())
                    .map(StopsFetchUiModel::success)
                    .onErrorReturn(t -> StopsFetchUiModel.failure(t.getMessage()))
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(result.isRefresh()) {
                        //screen.clearList();
                    }
                    else if(result.isSuccess()) {
                        screen.showLoadingView(false);
                        screen.displayStopPoint(result.getCompleteStopPoint());
                    }
                }, Throwable::printStackTrace));*/
        });
        googleApiClientProvider.connect();
    }

    private void fetchStopPoints(StopsScreen screen, LatLng latLng) {
        screen.showLoadingView(true);
        tflApi.stopPoint(latLng.latitude(), latLng.longitude())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(response -> {
                screen.showLoadingView(false);
                screen.displayStopPoints(response.stopPoints());
            }, screen::displayError);
    }

    private boolean isInLondon(@NonNull final LatLng latLng) {
        return (latLng.latitude() > 51.288923
            && latLng.latitude() < 51.706130
            && latLng.longitude() > -0.524597
            && latLng.longitude() < 0.280151);
    }

    void unbind() {
        googleApiClientProvider.disconnect();
        compositeDisposable.clear();
    }
}
