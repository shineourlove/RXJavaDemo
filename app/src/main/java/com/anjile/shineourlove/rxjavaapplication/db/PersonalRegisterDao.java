package com.anjile.shineourlove.rxjavaapplication.db;

import android.content.Context;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class PersonalRegisterDao {
    Context mContext;

    public PersonalRegisterDao(Context context) {
        this.mContext = context;
    }

    public void add(PersonalRegisterBean bean) {
        try {
            DataBaseHelper.getInstance(mContext).getPersonalRegisterDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PersonalRegisterBean> query() {
        List<PersonalRegisterBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getPersonalRegisterDao().queryForAll();
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
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getPersonalRegisterDao().updateBuilder();
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
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getPersonalRegisterDao().updateBuilder();
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
            DeleteBuilder deleteBuilder = DataBaseHelper.getInstance(mContext).getPersonalRegisterDao().deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            DataBaseHelper.getInstance(mContext).getPersonalRegisterDao()
                    .queryRaw("delete from personal_register");
            DataBaseHelper.getInstance(mContext).getPersonalRegisterDao().executeRaw("DELETE FROM sqlite_sequence WHERE name = 'personal_register'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
