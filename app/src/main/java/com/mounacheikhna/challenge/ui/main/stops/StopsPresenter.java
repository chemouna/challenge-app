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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
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

            if (latLng == null || !isInsideLondon(latLng)) {
                latLng = LatLng.create(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
            }

            //fetchStopPoints(screen, latLng);

            final Observable<StopPointResponse> stopPointObservable =
                tflApi.stopPoint(latLng.latitude(), latLng.longitude())
                    .share();

            final Observable<CompleteStopPoint> stopPointsWithArrivalsObservable =
                stopPointObservable.flatMap(
                    response -> Observable.fromIterable(response.stopPoints())
                        .flatMap(stopPoint -> {
                            Log.d("TEST",
                                "call to arrivals endpoint for stop point " + stopPoint);
                            return tflApi.arrivals(stopPoint.id())
                                .map(departures -> CompleteStopPoint.create(stopPoint, departures));
                        }));

            Observable.interval(0, 30, TimeUnit.SECONDS, Schedulers.io())
                //.startWith(0L)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    Log.d("TEST", "accept: Refresh emit -> clear adapter");
                    screen.clearStops();
                })
                .flatMap(l -> stopPointsWithArrivalsObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d("TEST",
                        "resp: called with stoppoint : " + response.stopPoint());
                    screen.displayStopPoint(CompleteStopPoint.create(response.stopPoint(), new ArrayList<>()));
                }, throwable -> Log.d("TEST", "Error : " + throwable));

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
        //screen.showLoadingView(true);
        tflApi.stopPoint(latLng.latitude(), latLng.longitude())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(response -> {
                //screen.showLoadingView(false);
                screen.displayStopPoints(response.stopPoints());
            }, screen::displayError);
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
}
