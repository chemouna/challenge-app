package com.mounacheikhna.challenge;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.mounacheikhna.challenge.annotation.ApplicationContext;
import com.mounacheikhna.challenge.annotation.ObserveOnScheduler;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Singleton;

@Module public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    public static Context provideApplicationContext(ChallengeApp app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    public static GoogleApiClient.Builder googleApiClientBuilder(ChallengeApp app) {
        return new GoogleApiClient.Builder(app).addApi(LocationServices.API);
    }

    @Provides
    @Singleton
    @ObserveOnScheduler
    Scheduler provideObserveOnScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
