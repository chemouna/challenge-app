package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import auto.parcel.AutoParcel;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;

@AutoParcel
public abstract class StopProperty implements Parcelable {
  public abstract String category();
  public abstract String key();
  public abstract String sourceSystemKey();
  public abstract String value();

}
