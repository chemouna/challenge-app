package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Arrival implements Parcelable {
  public abstract String lineId();
  public abstract String lineName();
  public abstract String platformName();
  @Nullable
  public abstract String destinationName();
  public abstract String towards();
  public abstract String expectedArrival();
  public abstract long timeToStation();

  public static TypeAdapter<Arrival> typeAdapter(Gson gson) {
    return new AutoValue_Arrival.GsonTypeAdapter(gson);
  }

}
