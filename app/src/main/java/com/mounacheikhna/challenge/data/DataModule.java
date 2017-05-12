package com.mounacheikhna.challenge.data;

import android.content.Context;
import android.util.Log;
import com.mounacheikhna.challenge.annotation.ApplicationContext;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import rx.schedulers.Schedulers;

@Module
public class DataModule {

    @Provides
    @Singleton
    DbOpenHelper provideOpenHelper(@ApplicationContext Context applicationContext) {
        return new DbOpenHelper(applicationContext);
    }

    @Provides
    @Singleton
    SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder().logger(message -> Log.d("TEST", message)).build();
    }

    @Provides
    @Singleton
    BriteDatabase provideDatabase(SqlBrite sqlBrite, DbOpenHelper helper) {
        return sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
    }

}
