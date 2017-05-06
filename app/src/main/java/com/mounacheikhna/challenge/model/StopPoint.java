package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import auto.parcel.AutoParcel;
import java.util.List;

@AutoParcel
public abstract class StopPoint implements Parcelable {
  public abstract String id();
  public abstract String naptanId();
  public abstract double lat();
  public abstract double lon();
  public abstract String commonName();
  public abstract double distance();
  public abstract List<StopProperty> additionalProperties();

}
