package com.paper.developwidget.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Paper on 14-12-11.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    public static String DB_NAME = "devwidget.db";
    public static int DB_VER = 1;
    DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VER);
    }
    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,AppPackeget.class);
        } catch (SQLException e) {
            Log.e("DatabaseHelper","create error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,AppPackeget.class,true);
        } catch (SQLException e) {
            Log.e("DatabaseHelper","update error");
        }
        onCreate(database,connectionSource);
    }
}
