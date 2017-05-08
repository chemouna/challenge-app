package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue public abstract class StopPoint {

    public static StopPoint create(String id, String naptanId, double lat, double lon,
        String commonName, double distance, List<StopProperty> additionalProperties) {
        return new AutoValue_StopPoint(id, naptanId, lat, lon, commonName, distance,
            additionalProperties);
        /*return AutoValue_StopPoint.builder()
            .naptanId(naptanId)
            .lat(lat)
            .lon(lon)
            .commonName(commonName)
            .distance(distance)
            .additionalProperties(additionalProperties)
            .build();*/
    }

    /*
    public static StopPoint.Builder builder() {
        return new AutoValue_StopPoint.Builder();
    }
    */

    public abstract String id();

    public abstract String naptanId();

    public abstract double lat();

    public abstract double lon();

    public abstract String commonName();

    public abstract double distance();

    public abstract List<StopProperty> additionalProperties();

    /*@AutoValue.Builder public interface Builder {

        Builder id(String id);

        Builder naptanId(String naptanId);

        Builder lat(double lat);

        Builder lon(double lon);

        Builder commonName(String commonName);

        Builder distance(double distance);

        Builder additionalProperties(List<StopProperty> additionalProperties);

        StopPoint build();
    }*/
}
