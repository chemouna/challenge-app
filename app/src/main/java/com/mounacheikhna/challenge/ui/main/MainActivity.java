package com.mounacheikhna.challenge.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.helpers.PermissionManager;
import com.mounacheikhna.challenge.ui.main.stops.StopsPresenter;
import com.mounacheikhna.challenge.ui.main.stops.StopsView;
import com.mounacheikhna.challenge.ui.savedstops.SavedStopsActivity;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saved_stops:
                SavedStopsActivity.startSavedStopActivity(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}