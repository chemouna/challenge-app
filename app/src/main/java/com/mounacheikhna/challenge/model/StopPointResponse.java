package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;

@AutoValue public abstract class StopPointResponse {

    public abstract List<StopPoint> stopPoints();

    public StopPointResponse() {
    }

    public static StopPointResponse create(List<StopPoint> stopPoints) {
        return new AutoValue_StopPointResponse(stopPoints);
        /* return AutoValue_StopPointResponse.builder().stopPoints(stopPoints).build();
        */
    }

    public static TypeAdapter<StopPointResponse> typeAdapter(Gson gson) {
        return new AutoValue_StopPointResponse.GsonTypeAdapter(gson);
    }

    /*public static StopPointResponse.Builder builder() {
        return new AutoValue_StopPointResponse.Builder();
    }
    */
    /*
    @AutoValue.Builder public interface Builder {
        Builder stopPoints(List<StopPoint> stopPoints);

        StopPointResponse build();
    }
    */
}
