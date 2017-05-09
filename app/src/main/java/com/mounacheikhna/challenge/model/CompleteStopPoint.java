package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
public abstract class CompleteStopPoint implements Parcelable {

    public abstract StopPoint stopPoint();
    public abstract List<Arrival> departures();

    public static CompleteStopPoint create(StopPoint stopPoint, List<Arrival> arrivals) {
        return new AutoValue_CompleteStopPoint(stopPoint, arrivals);
    }

}