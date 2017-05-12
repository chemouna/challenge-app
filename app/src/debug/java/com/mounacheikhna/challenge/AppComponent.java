package com.mounacheikhna.challenge;

import com.mounacheikhna.challenge.api.DebugApiModule;
import com.mounacheikhna.challenge.data.DataModule;
import com.mounacheikhna.challenge.ui.main.MainActivityModule;
import com.mounacheikhna.challenge.ui.savedstops.SavedStopsActivity;
import com.mounacheikhna.challenge.ui.stopdetails.StopDetailsActivityModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    AndroidInjectionModule.class, AppModule.class, DebugApiModule.class, DataModule.class,
    MainActivityModule.class, StopDetailsActivityModule.class,
    SavedStopsActivity.SavedStopsActivityModule.class
}) public interface AppComponent extends InjectGraph {

    @Component.Builder interface Builder {
        @BindsInstance
        Builder app(ChallengeApp app);
        AppComponent build();
    }
}
