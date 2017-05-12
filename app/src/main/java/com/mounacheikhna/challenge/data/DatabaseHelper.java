package com.mounacheikhna.challenge.data;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.Arrays;
import java.util.Set;

public class DatabaseHelper {

    public static QueryObservable createQuery(BriteDatabase db,
        SqlDelightStatement sqlDelightStatement) {
        Set<String> iter = sqlDelightStatement.tables;
        String str = sqlDelightStatement.statement;
        String[] args = sqlDelightStatement.args;
        return db.createQuery(iter, str, (String[]) Arrays.copyOf(args, args.length));
    }
}
