package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;

@AutoValue
public abstract class Departure implements Parcelable {
  public abstract String lineId();
  public abstract String lineName();
  public abstract String platformName();
  public abstract String destinationName();
  public abstract String towards();
  public abstract String expectedArrival();
  public abstract long timeToStation();

  public static TypeAdapter<Departure> typeAdapter(Gson gson) {
    return new AutoValue_Departure.GsonTypeAdapter(gson);
  }

}
