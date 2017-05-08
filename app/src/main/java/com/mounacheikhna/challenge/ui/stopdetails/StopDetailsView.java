package com.mounacheikhna.challenge.ui.stopdetails;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.CompleteStopPoint;

public class StopDetailsView extends LinearLayout {

    @Nullable private CompleteStopPoint completeStopPoint;

    public StopDetailsView(Context context) {
        this(context, null);
    }

    public StopDetailsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StopDetailsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view = LayoutInflater.from(context).inflate(R.layout.stop_details_view, this, true);
        ButterKnife.bind(this, view);
    }

    public void bind(CompleteStopPoint completeStopPoint) {
        this.completeStopPoint = completeStopPoint;

    }
}
