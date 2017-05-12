package com.mounacheikhna.challenge.ui.savedstops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.annotation.ScopeSingleton;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import javax.inject.Inject;

public class SavedStopsActivity extends AppCompatActivity {

    @BindView(R.id.saved_stops_container) SavedStopsListView savedStopsView;

    @Inject SavedStopsPresenter savedStopsPresenter;

    public static void startSavedStopActivity(Context context) {
        context.startActivity(new Intent(context, SavedStopsActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_stops_activity);
        ButterKnife.bind(this);
        setTitle(getString(R.string.saved_stops_title));
        savedStopsView.bind(savedStopsPresenter);
    }

    @ScopeSingleton(SavedStopsActivity.SavedStopsComponent.class) @Subcomponent
    public interface SavedStopsComponent extends AndroidInjector<SavedStopsActivity> {

        @Subcomponent.Builder abstract class Builder
            extends AndroidInjector.Builder<SavedStopsActivity> {
        }
    }

    @Module(subcomponents = SavedStopsComponent.class)
    public abstract class SavedStopsActivityModule {

        @Binds
        @IntoMap
        @ActivityKey(SavedStopsActivity.class)
        abstract AndroidInjector.Factory<? extends Activity> bindSavedStopsActivityInjectorFactory(
            SavedStopsComponent.Builder builder);
    }
}
