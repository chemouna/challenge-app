package com.mounacheikhna.challenge;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import com.facebook.stetho.Stetho;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import javax.inject.Inject;
import net.danlew.android.joda.JodaTimeAndroid;

public class ChallengeApp extends Application implements HasDispatchingActivityInjector {

    private static ChallengeApp instance;
    private AppComponent component;

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    public static AppComponent getAppComponent() {
        return instance.getComponent();
    }

    public AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerAppComponent.builder().app(this).build();
        component.inject(this);
        JodaTimeAndroid.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

}
