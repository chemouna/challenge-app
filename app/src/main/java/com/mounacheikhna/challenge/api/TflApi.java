package com.mounacheikhna.challenge.api;

import com.mounacheikhna.challenge.model.StopPointResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TflApi {

    @GET("/StopPoint?stopTypes=NaptanMetroStation&radius=900&useStopPointHierarchy=false&returnLines=True")
    Observable<StopPointResponse> stopPoint(@Query("lat") double latitude,
        @Query("lon") double longitude);

    //Observable<List<Departure>> departures(String stopPointId);
}
