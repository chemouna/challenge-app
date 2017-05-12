package com.mounacheikhna.challenge.api;

import com.mounacheikhna.challenge.model.Arrival;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.model.StopPointResponse;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TflApi {

    @GET("/StopPoint?stopTypes=NaptanMetroStation&useStopPointHierarchy=false&returnLines=True")
    Observable<StopPointResponse> stopPoint(@Query("lat") double latitude,
        @Query("lon") double longitude, @Query("radius") long radius);

    @GET("/StopPoint/{stopPointId}/Arrivals?mode=NaptanMetroStation")
    Observable<List<Arrival>> arrivals(@Path("stopPointId") String stopPointId);

    @GET("/Line/{lineId}/stoppoints")
    Observable<List<StopPoint>> lineStops(@Path("lineId") String lineId);

}

