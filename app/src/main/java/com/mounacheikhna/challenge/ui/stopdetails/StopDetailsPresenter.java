package com.mounacheikhna.challenge.ui.stopdetails;

import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.Line;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import javax.inject.Inject;

public class StopDetailsPresenter {

    private CompleteStopPoint completeStopPoint;
    private final TflApi api;
    private StopDetailsScreen screen;

    @Inject
    public StopDetailsPresenter(TflApi api) {
        this.api = api;
    }

    void bind(StopDetailsScreen screen, CompleteStopPoint completeStopPoint) {
        this.screen = screen;
        this.completeStopPoint = completeStopPoint;
        List<Line> lines = completeStopPoint.stopPoint().lines();
        if (lines.isEmpty()) return;
        final Line line = lines.get(0);
        api.lineStops(line.id())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(screen::displayStops);
    }
}
