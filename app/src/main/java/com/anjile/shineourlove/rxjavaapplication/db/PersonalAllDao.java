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

public class PersonalAllDao {
    Context mContext;

    public PersonalAllDao(Context context) {
        this.mContext = context;
    }

    public void add(PersonalAllBean bean) {
        try {
            DataBaseHelper.getInstance(mContext).getPersonalAllDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAll(List<PersonalAllBean> bean) {
        try {
            DataBaseHelper.getInstance(mContext).getPersonalAllDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PersonalAllBean> query() {
        List<PersonalAllBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getPersonalAllDao().queryForAll();
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
    public List<PersonalAllBean> queryDistinct(String key, String value, String imput) {
        List<PersonalAllBean> queryList = null;
        try {
            QueryBuilder builder = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
                    .queryBuilder();
            //builder.selectColumns("type_name").distinct();
            queryList = builder.where().eq(key, value).and().like("major_name", "%" + imput + "%").query();

            /*queryList = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
                    .queryBuilder().where().eq(key, value)
                    .query();*/
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    public List<PersonalAllBean> queryDistinct1(String key, String value, String imput) {
        List<PersonalAllBean> queryList = null;
        try {
            QueryBuilder builder = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
                    .queryBuilder();
            builder.selectColumns("major_name").distinct();
            queryList = builder.where().eq(key, value).and().like("major_name", "%" + imput + "%").query();

            /*queryList = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
                    .queryBuilder().where().eq(key, value)
                    .query();*/
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
    public List<PersonalAllBean> queryWhere(String key, String value) {
        List<PersonalAllBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
                    .queryBuilder().where().eq(key, value)
                    .query();
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    public PersonalAllBean queryFirst(String key, String value) {
        PersonalAllBean allBean = null;
        try {
            allBean = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
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
    public List<PersonalAllBean> queryDistinct(String key) {
        List<PersonalAllBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getPersonalAllDao()
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
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getPersonalAllDao().updateBuilder();
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
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getPersonalAllDao().updateBuilder();
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
            DeleteBuilder deleteBuilder = DataBaseHelper.getInstance(mContext).getPersonalAllDao().deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            DataBaseHelper.getInstance(mContext).getPersonalAllDao()
                    .queryRaw("delete from personal_all");
            DataBaseHelper.getInstance(mContext).getPersonalAllDao().executeRaw("DELETE FROM sqlite_sequence WHERE name = 'personal_all'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
