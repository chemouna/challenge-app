package com.mounacheikhna.challenge;

import com.mounacheikhna.challenge.api.ApiModule;
import com.mounacheikhna.challenge.ui.main.MainActivityModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    AndroidInjectionModule.class, AppModule.class, ApiModule.class, MainActivityModule.class
}) public interface AppComponent extends InjectGraph {

    @Component.Builder interface Builder {
        @BindsInstance
        Builder app(ChallengeApp app);

        AppComponent build();
    }
}
