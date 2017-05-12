package com.mounacheikhna.challenge.model;

import android.database.Cursor;
import com.google.auto.value.AutoValue;
import rx.functions.Func1;

@AutoValue public abstract class SavedStop implements SavedStopModel {

    public static final Factory<SavedStop> FACTORY = new Factory<>(AutoValue_SavedStop::new);

    public static final Func1<Cursor, SavedStop> MAPPER =
        cursor -> new Mapper<>(FACTORY).map(cursor);
}