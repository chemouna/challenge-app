package com.mounacheikhna.challenge.model;

import auto.parcel.AutoParcel;

@AutoParcel public abstract class LatLng {
    public abstract double latitude();

    public abstract double longitude();

    public static LatLng create(final double latitude, final double longitude) {
        return new AutoParcel_LatLng(latitude, longitude);
    }
}
