package com.mounacheikhna.challenge.ui.main.stops;

import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.StopPoint;
import io.reactivex.Observable;
import java.util.List;

//TODO: this screen has too many method (make it with only one or two)
public interface StopsScreen {

    boolean hasLocationPermission();

    void displayStopPoints(List<StopPoint> stopPoints);

    void displayError(Throwable throwable);

    void displayStopPoint(CompleteStopPoint stopPoint);

    void clearStops();

}
