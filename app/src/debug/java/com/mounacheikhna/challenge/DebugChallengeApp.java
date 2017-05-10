package com.mounacheikhna.challenge;

import android.os.StrictMode;
import com.facebook.stetho.Stetho;

public class DebugChallengeApp extends ChallengeApp {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        enableStrictMode();

        //LeakCanary.install(this);

    }

    private void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

}
