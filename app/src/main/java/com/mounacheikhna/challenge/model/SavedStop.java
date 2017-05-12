package com.mounacheikhna.challenge.model;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

@AutoValue public abstract class SavedStop implements SavedStopModel {

    public static final Factory<SavedStop> FACTORY = new Factory<>(AutoValue_SavedStop::new);

    public static final RowMapper<SavedStop> MAPPER = FACTORY.select_allMapper();
}