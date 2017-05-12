package com.mounacheikhna.challenge.ui.main;

import android.app.Activity;
import com.mounacheikhna.challenge.helpers.PermissionManager;

public class PermissionRequester {

    private final Activity activity;

    PermissionRequester(Activity activity) {
        this.activity = activity;
    }

    public void requestLocation() {
        PermissionManager.requestLocation(activity);
    }
}
