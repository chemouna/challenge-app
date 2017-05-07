package com.mounacheikhna.challenge.data;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionHelper {

    private static final String[] REQUEST_LOCATION = {
        "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"
    };
    private static final String COARSE_LOCATION_PERMISSION =
        "android.permission.ACCESS_COARSE_LOCATION";
    private static final String FINE_LOCATION_PERMISSION =
        "android.permission.ACCESS_FINE_LOCATION";

    private static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == 0;
    }

    public static boolean hasLocation(Context paramContext) {
        return (checkPermission(paramContext, COARSE_LOCATION_PERMISSION)) || (checkPermission(
            paramContext, FINE_LOCATION_PERMISSION));
    }

    public static void requestLocation(Activity activity) {
        ActivityCompat.requestPermissions(activity, REQUEST_LOCATION, 2);
    }
}
