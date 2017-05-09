package com.mounacheikhna.challenge.ui.main.stops;

import android.support.annotation.Nullable;
import com.mounacheikhna.challenge.model.CompleteStopPoint;

public class StopsFetchUiModel {

    private final boolean inProgress;
    private final boolean success;
    @Nullable private final String errorMessage;
    private final boolean refresh;
    private final CompleteStopPoint completeStopPoint;

    private StopsFetchUiModel(boolean inProgress, boolean success, @Nullable String errorMessage,
        boolean refresh, @Nullable CompleteStopPoint completeStopPoint) {
        this.inProgress = inProgress;
        this.success = success;
        this.errorMessage = errorMessage;
        this.refresh = refresh;
        this.completeStopPoint = completeStopPoint;
    }

    static StopsFetchUiModel inProgress() {
        return new StopsFetchUiModel(true, false, null, false, null);
    }

    static StopsFetchUiModel success(CompleteStopPoint completeStopPoint) {
        return new StopsFetchUiModel(false, true, null, false, completeStopPoint);
    }

    static StopsFetchUiModel failure(String errorMessage) {
        return new StopsFetchUiModel(false, false, errorMessage, false, null);
    }

    static StopsFetchUiModel refresh() {
        return new StopsFetchUiModel(true, false, null, false, null);
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public CompleteStopPoint getCompleteStopPoint() {
        return completeStopPoint;
    }
}
