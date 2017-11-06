package com.anjile.shineourlove.rxjavaapplication.db;

import android.content.Context;

import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeAllEntity;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class AptitudeAllDao {
    Context mContext;

    public AptitudeAllDao(Context context) {
        this.mContext = context;
    }

    public void add(AptitudeAllBean bean) {
        try {
            DataBaseHelper.getInstance(mContext).getAptitudeAllDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAll(List<AptitudeAllBean> bean) {
        try {
            DataBaseHelper.getInstance(mContext).getAptitudeAllDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AptitudeAllBean> query() {
        List<AptitudeAllBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getAptitudeAllDao().queryForAll();
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    /**
     * 查询某个关键字对应的数据列表
     *
     * @param key   关键字
     * @param value 值
     * @return 返回数据列表
     */
    public List<AptitudeAllBean> queryWhere(String key, String value) {
        List<AptitudeAllBean> queryList = null;
        try {
           QueryBuilder builder =  DataBaseHelper.getInstance(mContext).getAptitudeAllDao()
                    .queryBuilder();
            builder.selectColumns("aptitude_name").distinct();
            queryList = builder.where().eq(key, value).query();

            /*queryList = DataBaseHelper.getInstance(mContext).getAptitudeAllDao()
                    .queryBuilder().where().eq(key, value)
                    .query();*/
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    public AptitudeAllBean queryFirst(String key, String value) {
        AptitudeAllBean allBean = null;
        try {
            allBean = DataBaseHelper.getInstance(mContext).getAptitudeAllDao()
                    .queryBuilder().where().eq(key, value)
                    .queryForFirst();
            return allBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBean;
    }

    /**
     * 去重查询
     *
     * @param key 需要查询的字段名
     * @return 数据列表
     */
    public List<AptitudeAllBean> queryDistinct(String key) {
        List<AptitudeAllBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getAptitudeAllDao()
                    .queryBuilder().selectColumns(key).distinct()
                    .query();
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    /**
     * 更新某个字段的数据
     *
     * @param columnKey   字段
     * @param columnValue 字段对应的数据
     */
    public void updateOnly(int id, String columnKey, String columnValue) {
        try {
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getAptitudeAllDao().updateBuilder();
            builder.updateColumnValue(columnKey, columnValue);  //修改列为columnKey的值
            builder.where().eq("id", id + "");   //修改id为1的列
            builder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新设备名nickname
     *
     * @param columnValue 字段对应的数据
     */
    public void updateName(String key, String columnValue) {
        try {
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getAptitudeAllDao().updateBuilder();
            builder.updateColumnValue(key, columnValue);  //修改列为columnKey的值
            builder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定删除某一行数据
     *
     * @param id 需要删除的对应行的id
     */
    public void deleteOnly(int id) {
        try {
            DeleteBuilder deleteBuilder = DataBaseHelper.getInstance(mContext).getAptitudeAllDao().deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            DataBaseHelper.getInstance(mContext).getAptitudeAllDao()
                    .queryRaw("delete from aptitude_all");
            DataBaseHelper.getInstance(mContext).getAptitudeAllDao().executeRaw("DELETE FROM sqlite_sequence WHERE name = 'aptitude_all'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSaveIndex(final Context c, List<AptitudeAllEntity.DataBean> list) {
        AndroidDatabaseConnection adc = null;
        adc = new AndroidDatabaseConnection(DataBaseHelper.getInstance(c).getWritableDatabase(), true);
        try {
            Dao<AptitudeAllBean, Integer> dao= DataBaseHelper.getInstance(mContext).getAptitudeAllDao();
            dao.setAutoCommit(adc, false);
            Savepoint savepoint = adc.setSavePoint("aptitude_all");

            for (int i = 0; i < list.size(); i++) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
