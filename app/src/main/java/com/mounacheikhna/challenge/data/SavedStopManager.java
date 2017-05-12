package com.mounacheikhna.challenge.data;

import com.mounacheikhna.challenge.model.Line;
import com.mounacheikhna.challenge.model.SavedStop;
import com.mounacheikhna.challenge.model.SavedStopModel;
import com.mounacheikhna.challenge.model.StopPoint;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages operations on {@link SavedStop} like fetching and inserting in a data source (like a
 * database).
 */
@Singleton public class SavedStopManager {

    private final BriteDatabase db;

    @Inject
    public SavedStopManager(BriteDatabase db) {
        this.db = db;
    }

    public Observable<List<SavedStop>> getStops() {
        SavedStopModel.Factory factory = SavedStop.FACTORY;
        QueryObservable observable = DatabaseHelper.createQuery(db, factory.select_all());
        return RxJavaInterop.toV2Observable(observable.mapToList(SavedStop.MAPPER));
    }

    public void save(StopPoint stopPoint) {
        final Line line = stopPoint.lines().isEmpty() ? null : stopPoint.lines().get(0);
        db.insert(SavedStop.TABLE_NAME, SavedStop.FACTORY.marshal()
            .lat((long) stopPoint.lat())
            .lon((long) stopPoint.lon())
            .commonName(stopPoint.commonName())
            .distance((long) stopPoint.distance())
            .lineId(line != null ? line.id() : null)
            .lineName(line != null ? line.name() : null)
            .asContentValues());
    }
}
