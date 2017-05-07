package com.mounacheikhna.challenge.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.ui.main.stops.StopsPresenter;
import com.mounacheikhna.challenge.ui.main.stops.StopsView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.stops_view) StopsView stopsView;

    @Inject StopsPresenter stopsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        stopsView.bind(stopsPresenter);
    }

}
