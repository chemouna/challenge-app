package com.mounacheikhna.challenge.helpers;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.location.LocationServices;
import com.mounacheikhna.challenge.model.LatLng;
import javax.inject.Inject;

public class LocationRequester {

  @Inject
  public LocationRequester() {
  }

  @Nullable
  public LatLng lastLocation(@NonNull final GoogleApiClientProvider apiClientWrapper) {
    final Location lastLocation =
        LocationServices.FusedLocationApi.getLastLocation(apiClientWrapper.getApiClient());
    return lastLocation == null ? null
        : LatLng.create(lastLocation.getLatitude(), lastLocation.getLongitude());
  }
}
