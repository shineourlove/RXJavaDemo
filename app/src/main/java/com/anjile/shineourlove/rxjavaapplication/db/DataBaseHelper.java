package com.anjile.shineourlove.rxjavaapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by CP on 2016/9/27.
 * 数据库管理类
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "user_data.db";   //数据库文件名字
    private Dao<WorkResumeBean, Integer> workResumeDao; //参数1：DAO操作的类；参数2：标记数据表的ID


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkResumeBean.class);  //创建一张表; 传入链接源(ConnectionSource)参数

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    private static DataBaseHelper dataBaseHelper;

    //单例模式  获取dataBaseHelper对象
    public static synchronized DataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            synchronized (DataBaseHelper.class) {
                dataBaseHelper = new DataBaseHelper(context);
            }
        }
        return dataBaseHelper;
    }

    public Dao<WorkResumeBean, Integer> getWorkResumeDao() {
        if (workResumeDao == null) {
            try {
                workResumeDao = getDao(WorkResumeBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return workResumeDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
    }
}
