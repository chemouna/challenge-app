package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
@AutoValue public abstract class StopPoint implements Parcelable {

    public static StopPoint create(String id, String naptanId, double lat, double lon,
        String commonName, double distance, List<StopProperty> additionalProperties,
        List<Line> lines) {
        return new AutoValue_StopPoint(id, naptanId, lat, lon, commonName, distance,
            additionalProperties, lines);
    }

    public abstract String id();

    public abstract String naptanId();

    public abstract double lat();

    public abstract double lon();

    public abstract String commonName();

    public abstract double distance();

    public abstract List<StopProperty> additionalProperties();

    public abstract List<Line> lines();

    public static TypeAdapter<StopPoint> typeAdapter(Gson gson) {
        return new AutoValue_StopPoint.GsonTypeAdapter(gson);
    }
}
