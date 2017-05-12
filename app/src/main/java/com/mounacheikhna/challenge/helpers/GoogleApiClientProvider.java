package com.mounacheikhna.challenge.helpers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import javax.inject.Inject;

public class GoogleApiClientProvider {
  private GoogleApiClient apiClient;

  @Inject
  public GoogleApiClientProvider(@NonNull final GoogleApiClient.Builder builder) {
    apiClient = builder.build();
  }

  public void connect() {
    apiClient.connect();
  }

  public void disconnect() {
    if (apiClient != null) {
      apiClient.disconnect();
    }
    apiClient = null;
  }

  public GoogleApiClient getApiClient() {
    return apiClient;
  }

  public void registerConnectionCallbacks(@NonNull final ConnectionCallback connectionCallback) {
    apiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
      @Override
      public void onConnected(@Nullable final Bundle bundle) {
        connectionCallback.onConnected();
      }

      @Override
      public void onConnectionSuspended(int i) {

      }
    });
  }

  public interface ConnectionCallback {
    void onConnected();
  }
}
