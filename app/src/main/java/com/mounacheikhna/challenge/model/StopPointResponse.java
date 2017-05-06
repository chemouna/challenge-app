package com.mounacheikhna.challenge.model;

import auto.parcel.AutoParcel;
import java.util.List;

@AutoParcel public abstract class StopPointResponse {
    public abstract List<StopPoint> stopPoints();
}
