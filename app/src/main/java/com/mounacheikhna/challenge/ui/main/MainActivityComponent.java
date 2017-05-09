package com.mounacheikhna.challenge.ui.main;

import com.mounacheikhna.challenge.annotation.ScopeSingleton;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeSingleton(MainActivityComponent.class)
@Subcomponent(modules = { MainActivityComponent.MainModule.class })
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder public abstract class Builder
        extends AndroidInjector.Builder<MainActivity> {
    }

    @Module public class MainModule {
        @ScopeSingleton(MainActivityComponent.class)
        @Provides
        PermissionRequester providePermissionRequester(MainActivity activity) {
            return new PermissionRequester(activity);
        }
    }
}
