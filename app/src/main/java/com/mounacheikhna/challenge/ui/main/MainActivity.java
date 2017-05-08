package com.mounacheikhna.challenge.ui.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.ui.main.stops.StopsPresenter;
import com.mounacheikhna.challenge.ui.main.stops.StopsView;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.stops_view) StopsView stopsView;

    @Inject StopsPresenter stopsPresenter;
    @Inject PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        stopsView.bind(stopsPresenter);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
        @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.permissionManager.requestPermissionsResult(permissions, grantResults);

        /*switch (requestCode) {
            case REQUEST_CODE_REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.locationPermissionGranted();
                } else {
                    presenter.locationPermissionDenied();
                }
        }*/
    }

}
