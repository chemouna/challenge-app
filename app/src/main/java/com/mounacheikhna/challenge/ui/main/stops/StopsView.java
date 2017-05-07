package com.mounacheikhna.challenge.ui.main.stops;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.ChallengeApp;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.data.GoogleApiClientProvider;
import com.mounacheikhna.challenge.data.LocationRequester;
import com.mounacheikhna.challenge.data.PermissionManager;
import com.mounacheikhna.challenge.ui.main.MainActivity;
import com.mounacheikhna.challenge.ui.main.MainActivityComponent;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import javax.inject.Inject;

public class StopsView extends LinearLayout implements StopsScreen {

    @BindView(R.id.stops_rv) RecyclerView stopsRv;

    private StopsPresenter presenter;

    public StopsView(Context context) {
        this(context, null);
    }

    public StopsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StopsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view = LayoutInflater.from(context).inflate(R.layout.stops_view, this, true);

        ButterKnife.bind(this, view);
    }

    public void bind(StopsPresenter stopsPresenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean hasLocationPermission() {
        return PermissionManager.hasLocation(getContext());
    }

    @Override
    public void requestLocationPermission() {
        //PermissionManager.requestLocation(activity);
    }

    @Override
    public Observable<Object> stopPointSelected() {
        return null;
    }

    @Override
    public void displayStopDetails(Object o) {

    }

    @Override
    public void showLoadingView(boolean b) {

    }

    @Override
    public void showNoStopsView(boolean b) {

    }

    @Override
    public void setStopPoints(Object stopPoints) {

    }
}