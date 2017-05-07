package com.mounacheikhna.challenge.ui.main;

import android.app.Activity;
import com.mounacheikhna.challenge.data.PermissionHelper;

public class PermissionRequester {

    private final Activity activity;

    public PermissionRequester(Activity activity) {
        this.activity = activity;
    }

    public void requestLocation() {
        PermissionHelper.requestLocation(activity);
    }
}
