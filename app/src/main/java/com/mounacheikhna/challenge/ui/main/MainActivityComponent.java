package com.mounacheikhna.challenge.ui.main;

import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = { MainActivityComponent.MainModule.class })
public interface MainActivityComponent  extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

    @Module
    public class MainModule {

    }


}
