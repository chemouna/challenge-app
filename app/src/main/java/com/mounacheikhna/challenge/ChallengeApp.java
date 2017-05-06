package com.mounacheikhna.challenge;

import android.app.Application;

public class ChallengeApp extends Application {

    private static ChallengeApp instance;
    private AppComponent component;

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
        //component = DaggerAppComponent.builder().app(this).build();
        //component.inject(this);
    }
}
