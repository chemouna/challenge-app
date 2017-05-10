package com.mounacheikhna.challenge.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import javax.annotation.concurrent.Immutable;

@Immutable
@AutoValue public abstract class Line implements Parcelable {

    public abstract String id();
    public abstract String name();
    public abstract String uri();
    public abstract String type();

    public static Line create(String id, String name, String uri, String type) {
        return new AutoValue_Line(id, name, uri, type);
    }

    public static TypeAdapter<Line> typeAdapter(Gson gson) {
        return new AutoValue_Line.GsonTypeAdapter(gson);
    }
}
