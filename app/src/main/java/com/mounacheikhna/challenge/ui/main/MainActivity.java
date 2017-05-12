package com.mounacheikhna.challenge.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.helpers.PermissionManager;
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
        setTitle(R.string.nearby_tube_stations);

        stopsView.bind(stopsPresenter);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode,
        @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.permissionManager.requestPermissionsResult(permissions, grantResults);
    }

}
