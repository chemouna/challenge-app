package com.mounacheikhna.challenge.ui.main.stops;

import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.Departure;
import com.mounacheikhna.challenge.model.StopPoint;
import io.reactivex.Observable;
import java.util.List;

//TODO: this screen has too many method (make it with only one or two)
public interface StopsScreen {

    boolean hasLocationPermission();

    Observable<Object> stopPointSelected();

    void displayStopDetails(Object o);

    void showLoadingView(boolean b);

    void showNoStopsView(boolean b);

    //void displayStopPoints(List<StopPoint> stopPoints);

    void displayStopPoint(CompleteStopPoint result);
}
