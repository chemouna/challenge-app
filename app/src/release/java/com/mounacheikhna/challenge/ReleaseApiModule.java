package com.mounacheikhna.challenge;

import com.mounacheikhna.challenge.api.ApiModule;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module
public class ReleaseApiModule extends ApiModule {

    @Provides
    @Singleton
    public OkHttpClient provideApiClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

}
