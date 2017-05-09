package com.mounacheikhna.challenge.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mounacheikhna.challenge.BuildConfig;
import dagger.Module;
import dagger.Provides;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

@Module public class DebugApiModule extends ApiModule {

    @DebugInterceptors
    @Singleton
    @Provides
    List<Interceptor> provideDebugInterceptors() {
        final LoggingInterceptor loggingInterceptor =
            new LoggingInterceptor.Builder().loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build();
        return Arrays.asList(new HttpLoggingInterceptor(), loggingInterceptor);
    }

    @DebugNetworkInterceptors
    @Singleton
    @Provides
    List<Interceptor> provideDebugNetworkInterceptors() {
        return Arrays.asList(new StethoInterceptor());
    }

    @Provides
    @Singleton
    public OkHttpClient provideApiClient(OkHttpClient.Builder builder,
        @DebugInterceptors List<Interceptor> debugInterceptors,
        @DebugNetworkInterceptors List<Interceptor> debugNetworkInterceptors) {

        for (Interceptor networkInterceptor : debugNetworkInterceptors) {
            builder.addNetworkInterceptor(networkInterceptor);
        }

        for (Interceptor debugInterceptor : debugInterceptors) {
            builder.addInterceptor(debugInterceptor);
        }

        return builder.build();
    }
}
