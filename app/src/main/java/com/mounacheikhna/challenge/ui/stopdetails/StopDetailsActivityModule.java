package com.mounacheikhna.challenge.ui.stopdetails;

import android.app.Activity;
import com.mounacheikhna.challenge.ui.stopdetails.StopDetailsActivity.StopDetailsComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = StopDetailsComponent.class)
public abstract class StopDetailsActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(StopDetailsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindStopDetailsActivityInjectorFactory(
        StopDetailsComponent.Builder builder);
}

