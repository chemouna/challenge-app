package com.mounacheikhna.challenge.data;

import com.mounacheikhna.challenge.model.SavedStop;
import com.mounacheikhna.challenge.model.SavedStopModel;
import com.mounacheikhna.challenge.model.StopPoint;
import com.squareup.sqlbrite.BriteDatabase;
import io.reactivex.Observable;
import java.util.List;

public class StopPointManager {

    private final BriteDatabase db;

    public StopPointManager(BriteDatabase db) {
        this.db = db;
    }

    public Observable<List<SavedStop>> getStops() {
        return db.createQuery(SavedStopModel.TABLE, User.GET_USERS)
            .mapToList(User.MAPPER);
    }

    public void save(SavedStop savedStop) {
        //first transform it into SavedStop

    }

    public void save(StopPoint stopPoint) {

    }
}
