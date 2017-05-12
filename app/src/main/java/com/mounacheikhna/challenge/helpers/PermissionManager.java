package com.mounacheikhna.challenge.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.jakewharton.rxrelay2.PublishRelay;
import com.mounacheikhna.challenge.annotation.ApplicationContext;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class PermissionManager {

    public static final String[] REQUEST_LOCATION_PERMISSIONS = {
        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 2;

    private final PublishRelay<String> denials = PublishRelay.create();
    private final PublishRelay<String> grants = PublishRelay.create();
    private final Context context;

    @Inject
    public PermissionManager(@ApplicationContext Context context) {
        this.context = context;
    }

    private static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == 0;
    }

    public static boolean hasLocation(Context context) {
        return (checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION))
            || (checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION));
    }

    public static void requestLocation(Activity activity) {
        ActivityCompat.requestPermissions(activity, REQUEST_LOCATION_PERMISSIONS,
            REQUEST_CODE_LOCATION_PERMISSION);
    }

    public void requestPermissionsResult(String[] permissions, int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                this.grants.accept(permissions[i]);
            } else {
                this.denials.accept(permissions[i]);
            }
        }
    }

    public Observable<String> denials() {
        return this.denials;
    }

    private Observable<Boolean> granted(String permission) {
        if (checkPermission(this.context, permission)) {
            return Observable.just(Boolean.TRUE);
        }
        return this.grants.filter(s -> s.equals(permission)).map(s -> true).take(1);
    }

    public Observable<Boolean> anyGanted(String[] permissions) {
        return Observable.fromArray(permissions).flatMap(this::granted);
    }
}
