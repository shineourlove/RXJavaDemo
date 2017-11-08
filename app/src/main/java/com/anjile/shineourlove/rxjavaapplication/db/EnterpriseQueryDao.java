package com.anjile.shineourlove.rxjavaapplication.db;

import android.content.Context;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class EnterpriseQueryDao {
    Context mContext;

    public EnterpriseQueryDao(Context context) {
        this.mContext = context;
    }

    public void add(EnterpriseQueryBean bean) {
        try {
            DataBaseHelper.getInstance(mContext).getEnterpriseQueryDao().create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EnterpriseQueryBean> query() {
        List<EnterpriseQueryBean> queryList = null;
        try {
            queryList = DataBaseHelper.getInstance(mContext).getEnterpriseQueryDao().queryForAll();
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
    public void updateOnly(String columnKey, String columnValue) {
        List<EnterpriseQueryBean> queryList = null;
        try {
            queryList = query();
            if (queryList.size() == 0) {
                add(new EnterpriseQueryBean());
                queryList = query();
            }
            UpdateBuilder builder = DataBaseHelper.getInstance(mContext).getEnterpriseQueryDao().updateBuilder();
            builder.updateColumnValue(columnKey, columnValue);  //修改列为columnKey的值
            builder.where().eq("id", queryList.get(0).getId());   //修改id为1的列
            builder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            DataBaseHelper.getInstance(mContext).getUserInfoDao()
                    .queryRaw("delete from enterprise_query");
            DataBaseHelper.getInstance(mContext).getUserInfoDao().executeRaw("DELETE FROM sqlite_sequence WHERE name = 'enterprise_query'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
