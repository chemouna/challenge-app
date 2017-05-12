package com.mounacheikhna.challenge.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.mounacheikhna.challenge.model.SavedStop;

/**
 * Helper for creating and upgrading the database.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String APP_DB = "app.db";

    DbOpenHelper(Context context) {
        super(context, APP_DB, null, DB_VERSION);
    }

    public void recreateDb() {
        recreateDb(getWritableDatabase());
    }

    private void recreateDb(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + SavedStop.TABLE_NAME);
        onCreate(db);
    }

    @TargetApi(16)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < 16) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
        db.execSQL(SavedStop.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != DB_VERSION) {
            recreateDb(db);
        }
    }
}
