package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;
import javax.annotation.concurrent.Immutable;

@Immutable
@AutoValue public abstract class LatLng {

    public abstract double latitude();
    public abstract double longitude();

    public static LatLng create(final double latitude, final double longitude) {
        return new AutoValue_LatLng(latitude, longitude);
    }

}
