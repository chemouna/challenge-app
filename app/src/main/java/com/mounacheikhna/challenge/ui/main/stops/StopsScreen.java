package com.mounacheikhna.challenge.ui.main.stops;

import com.mounacheikhna.challenge.model.CompleteStopPoint;

interface StopsScreen {

    boolean hasLocationPermission();

    void displayStopPoint(CompleteStopPoint stopPoint);

    void clearStops();

    void onLocationDenied();
}
