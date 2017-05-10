package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
@AutoValue public abstract class StopPointResponse {

    public abstract List<StopPoint> stopPoints();

    public StopPointResponse() {
    }

    public static StopPointResponse create(List<StopPoint> stopPoints) {
        return new AutoValue_StopPointResponse(stopPoints);
    }

    public static TypeAdapter<StopPointResponse> typeAdapter(Gson gson) {
        return new AutoValue_StopPointResponse.GsonTypeAdapter(gson);
    }

}
