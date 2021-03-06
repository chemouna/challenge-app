package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
@AutoValue
public abstract class CompleteStopPoint implements Parcelable {

    public abstract StopPoint stopPoint();
    public abstract List<Arrival> arrivals();

    public static CompleteStopPoint create(StopPoint stopPoint, List<Arrival> arrivals) {
        return new AutoValue_CompleteStopPoint(stopPoint, arrivals);
    }

}