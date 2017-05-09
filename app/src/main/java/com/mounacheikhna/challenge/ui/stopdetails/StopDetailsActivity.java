package com.mounacheikhna.challenge.ui.stopdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.annotation.ScopeSingleton;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import dagger.Subcomponent;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import javax.inject.Inject;

public class StopDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_COMPLETE_STOP_POINT = "extra_complete_stop_point";
    private CompleteStopPoint completeStopPoint;

    @BindView(R.id.stop_details_view) StopDetailsView stopDetailsView;
    @Inject StopDetailsPresenter stopDetailsPresenter;

    public static void start(Context context, CompleteStopPoint item) {
        Intent intent = new Intent(context, StopDetailsActivity.class);
        intent.putExtra(EXTRA_COMPLETE_STOP_POINT, item);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_details_activity);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(EXTRA_COMPLETE_STOP_POINT)) {
            completeStopPoint = getIntent().getParcelableExtra(EXTRA_COMPLETE_STOP_POINT);
            setTitle(completeStopPoint.stopPoint().commonName());

            stopDetailsView.bind(stopDetailsPresenter, completeStopPoint);
        }
    }

    @ScopeSingleton(StopDetailsComponent.class) @Subcomponent public interface StopDetailsComponent
        extends AndroidInjector<StopDetailsActivity> {

        @Subcomponent.Builder abstract class Builder
            extends AndroidInjector.Builder<StopDetailsActivity> {
        }
    }
}
