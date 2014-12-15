package com.paper.developwidget.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Paper on 14-12-11.
 */
public class AppPacketManager {
    protected DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSQLiteDatabase;
    protected Dao<AppPackeget,Integer> mBeanDao;
    public AppPacketManager(Context context){
        mDatabaseHelper = new DatabaseHelper(context);
        try {
            mBeanDao =  mDatabaseHelper.getDao(AppPackeget.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(AppPackeget appPackeget){
        try {
            mBeanDao.createIfNotExists(appPackeget);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(List<AppPackeget> list){
        for (AppPackeget packeget:list){
            save(packeget);
        }
    }

    public List<AppPackeget> queryMix(String keyword){
        List<AppPackeget> ret =null;
        QueryBuilder qb = mBeanDao.queryBuilder();
        try {
            ret = qb.orderBy("createtime",false)
                    .where().like("name","%"+keyword+"%")
                    .or().like("pakage","%"+keyword+"%")
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
