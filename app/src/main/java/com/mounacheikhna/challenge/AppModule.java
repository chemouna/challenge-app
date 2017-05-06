package com.mounacheikhna.challenge;

import android.content.Context;
import com.mounacheikhna.challenge.annotation.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    public Context provideApplicationContext(ChallengeApp app) {
        return app.getApplicationContext();
    }
}
