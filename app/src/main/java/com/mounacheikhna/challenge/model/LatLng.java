package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class LatLng {

    public static LatLng create(final double latitude, final double longitude) {
        return new AutoValue_LatLng(latitude, longitude);
        //return AutoValue_LatLng.builder().latitude(latitude).longitude(longitude).build();
    }

    public abstract double latitude();

    public abstract double longitude();

    /*
    public static LatLng.Builder builder() {
        return new AutoValue_LatLng.Builder();
    }

    @AutoValue.Builder public interface Builder {
        Builder latitude(double latitude);

        Builder longitude(double longitude);

        Builder describe
        LatLng build();
    }
    */
}
