package com.anjile.shineourlove.rxjavaapplication.db;

import android.content.Context;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class EnterprisePerformanceSettingDao {
    Context mContext;

    public EnterprisePerformanceSettingDao(Context context) {
        this.mContext = context;
    }

    public void add(EnterprisePerformanceSettingBean bean) {
        try {
            DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EnterprisePerformanceSettingBean> query() {
        List<EnterprisePerformanceSettingBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().queryForAll();
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
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().updateBuilder();
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
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().updateBuilder();
            builder.updateColumnValue(key, columnValue);  //修改列为columnKey的值
            builder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询某个关键字对应的数据列表
     *
     * @param key   关键字
     * @param value 值
     * @return 返回数据列表
     */
    public List<EnterprisePerformanceSettingBean> queryWhere(String key, String value) {
        List<EnterprisePerformanceSettingBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao()
                    .queryBuilder().where().eq(key, value)
                    .query();
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    /**
     * 指定删除某一行数据
     *
     * @param id 需要删除的对应行的id
     */
    public void deleteOnly(int id) {
        try {
            DeleteBuilder deleteBuilder = DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().queryRaw("delete from enterprise_performance_setting");
            DataBaseHelper.getInstance(mContext).getEnterprisePerformanceSettingDao().executeRaw("DELETE FROM sqlite_sequence WHERE name = 'enterprise_performance_setting'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
