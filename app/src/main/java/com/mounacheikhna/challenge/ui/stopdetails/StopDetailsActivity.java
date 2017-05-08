package com.mounacheikhna.challenge.ui.stopdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.ui.recyclerview.RecyclerViewWithEmptyProgress;

public class StopDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_COMPLETE_STOP_POINT = "extra_complete_stop_point";
    private CompleteStopPoint completeStopPoint;

    @BindView(R.id.rv) RecyclerViewWithEmptyProgress stopDetailsRv;

    public static void start(Context context, CompleteStopPoint item) {
        Intent intent = new Intent(context, StopDetailsActivity.class);
        intent.putExtra(EXTRA_COMPLETE_STOP_POINT, item);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_details_activity);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(EXTRA_COMPLETE_STOP_POINT)) {
            completeStopPoint = getIntent().getParcelableExtra(EXTRA_COMPLETE_STOP_POINT);
            setTitle(completeStopPoint.stopPoint().commonName());
        }
    }
}
