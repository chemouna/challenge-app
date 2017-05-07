package com.mounacheikhna.challenge.ui.main.stops;

import io.reactivex.Observable;

public interface StopsScreen {

    boolean hasLocationPermission();

    void requestLocationPermission();

    Observable<Object> stopPointSelected();

    void displayStopDetails(Object o);

    void showLoadingView(boolean b);

    void showNoStopsView(boolean b);

    void setStopPoints(Object stopPoints);
}
