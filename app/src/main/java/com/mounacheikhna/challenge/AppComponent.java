package com.mounacheikhna.challenge;

import com.mounacheikhna.challenge.api.ApiModule;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class, ApiModule.class })
public interface AppComponent extends InjectGraph {

    @Component.Builder interface Builder {
        @BindsInstance
        Builder app(ChallengeApp app);
        AppComponent build();
    }

}
