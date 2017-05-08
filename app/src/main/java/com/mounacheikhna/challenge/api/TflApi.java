package com.mounacheikhna.challenge.api;

import com.mounacheikhna.challenge.model.Departure;
import com.mounacheikhna.challenge.model.StopPointResponse;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TflApi {

    @GET("/StopPoint?stopTypes=NaptanMetroStation&radius=900&useStopPointHierarchy=false&returnLines=True")
    Observable<StopPointResponse> stopPoint(@Query("lat") double latitude,
        @Query("lon") double longitude);

    @GET("/StopPoint/{stopPointId}/Arrivals")
    Observable<List<Departure>> departures(@Path("stopPointId") String stopPointId);
}
