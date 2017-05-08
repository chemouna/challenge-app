package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class StopProperty {

    public static StopProperty create(String category, String key, String sourceSystemKey,
        String value) {
        return new AutoValue_StopProperty(category, key, sourceSystemKey, value);
    /*    return builder().category(category)
            .key(key)
            .sourceSystemKey(sourceSystemKey)
            .value(value)
            .build();
    */
    }

    /*public static Builder builder() {
        return new AutoValue_StopProperty.Builder();
    }
    */

    public abstract String category();

    public abstract String key();

    public abstract String sourceSystemKey();

    public abstract String value();

  /*  @AutoValue.Builder public interface Builder {
        Builder category(String category);

        Builder key(String key);

        Builder sourceSystemKey(String sourceSystemKey);

        Builder value(String value);

        StopProperty build();
    }*/
}
