package com.mounacheikhna.challenge.api;

import com.mounacheikhna.challenge.model.StopPoint;
import io.reactivex.Observable;
import java.util.List;

public interface TflApi {

    Observable<List<StopPoint>> stopPoint(double latitude, double longitude);

    //Observable<List<Departure>> departures(String stopPointId);

}
