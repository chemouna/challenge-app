package com.mounacheikhna.challenge.data;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.jakewharton.rxrelay2.PublishRelay;
import com.mounacheikhna.challenge.annotation.ApplicationContext;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PermissionManager {

    private static final String[] REQUEST_LOCATION = {
        "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"
    };
    private static final String COARSE_LOCATION_PERMISSION =
        "android.permission.ACCESS_COARSE_LOCATION";
    private static final String FINE_LOCATION_PERMISSION =
        "android.permission.ACCESS_FINE_LOCATION";

    private final Context context;
    private final PublishRelay<String> denials = PublishRelay.create();
    private final PublishRelay<String> grants = PublishRelay.create();
    private final PublishRelay<Object> refresh = PublishRelay.create();

    @Inject
    public PermissionManager(@ApplicationContext Context context) {
        this.context = context;
    }

    private static boolean checkPermission(Context paramContext, String paramString) {
        return ContextCompat.checkSelfPermission(paramContext, paramString) == 0;
    }

    public static boolean hasLocation(Context paramContext) {
        return (checkPermission(paramContext, COARSE_LOCATION_PERMISSION)) || (checkPermission(
            paramContext, FINE_LOCATION_PERMISSION));
    }

    public static void requestLocation(Activity activity) {
        ActivityCompat.requestPermissions(activity, REQUEST_LOCATION, 2);
    }

    public Observable<String> denials() {
        return this.denials;
    }

    /*public Observable<Boolean> granted(@NonNull String permission) {
        if (checkPermission(this.context, permission)) {
            return Observable.just(Boolean.TRUE);
        }
        PublishRelay relay = this.grants;
        return Observable.merge(relay.map(PermissionManager..Lambda .1.lambdaFactory$(permission)),
        this.refresh.map(PermissionManager..Lambda .2.lambdaFactory$(this, permission))).
        filter(PermissionManager..Lambda .3.lambdaFactory$())
        .take(1).startWith(Boolean.FALSE);
    }*/

    /*public void requestPermissionsResult(String[] paramArrayOfString, int[] paramArrayOfInt) {
        int i = 0;
        int j = paramArrayOfString.length;
        if (i < j) {
            if (paramArrayOfInt[i] == 0) this.grants.call(paramArrayOfString[i]);
            while (true) {
                i++;
                break;
                if (paramArrayOfInt[i] == -1) this.denials.call(paramArrayOfString[i]);
            }
        }
    }*/
}
