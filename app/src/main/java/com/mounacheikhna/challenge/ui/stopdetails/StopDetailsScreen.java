package com.mounacheikhna.challenge.ui.stopdetails;

import com.mounacheikhna.challenge.model.LatLng;
import com.mounacheikhna.challenge.model.StopPoint;
import java.util.List;

interface StopDetailsScreen {

    void displayStops(List<StopPoint> stopPoints);

    void selectLocationStop(LatLng latLng);
}
