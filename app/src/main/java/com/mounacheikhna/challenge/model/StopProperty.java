package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue public abstract class StopProperty {

    public static StopProperty create(String category, String key, String sourceSystemKey,
        String value) {
        return new AutoValue_StopProperty(category, key, sourceSystemKey, value);
    }

    public abstract String category();

    public abstract String key();

    public abstract String sourceSystemKey();

    public abstract String value();

    public static TypeAdapter<StopProperty> typeAdapter(Gson gson) {
        return new AutoValue_StopProperty.GsonTypeAdapter(gson);
    }

}
