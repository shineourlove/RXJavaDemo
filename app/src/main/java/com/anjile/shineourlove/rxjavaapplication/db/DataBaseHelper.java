package com.anjile.shineourlove.rxjavaapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by shineourlove on 2016/9/27.
 * 数据库管理类
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "user_data.db";   //数据库文件名字
    private Dao<WorkResumeBean, Integer> workResumeDao; //参数1：DAO操作的类；参数2：标记数据表的ID
    private Dao<UserInfoBean, Integer> userInfoDao;
    private Dao<AptitudeAllBean, Integer> aptitudeAllDao;
    private Dao<AptitudeSelectedBean, Integer> selectedBeanDao;
    private Dao<EnterprisePerformanceSettingBean, Integer> enterprisePerformanceSettingDao;
    private Dao<EnterpriseQueryBean, Integer> enterpriseQueryDao;
    private Dao<PersonalRegisterBean, Integer> personalRegisterDao;
    private Dao<PersonalTitleBean, Integer> personalTitleDao;
    private Dao<PersonalManagerBean, Integer> personalManagerDao;
    private Dao<PurposeAllBean, Integer> purposeAllDao;
    private Dao<PersonalAllBean, Integer> personalAllDao;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkResumeBean.class);  //创建一张表; 传入链接源(ConnectionSource)参数
            TableUtils.createTable(connectionSource, UserInfoBean.class);
            TableUtils.createTable(connectionSource, AptitudeAllBean.class);
            TableUtils.createTable(connectionSource, AptitudeSelectedBean.class);
            TableUtils.createTable(connectionSource, EnterprisePerformanceSettingBean.class);
            TableUtils.createTable(connectionSource, EnterpriseQueryBean.class);
            TableUtils.createTable(connectionSource, PersonalRegisterBean.class);
            TableUtils.createTable(connectionSource, PersonalTitleBean.class);
            TableUtils.createTable(connectionSource, PersonalManagerBean.class);
            TableUtils.createTable(connectionSource, PurposeAllBean.class);
            TableUtils.createTable(connectionSource, PersonalAllBean.class);
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

    public Dao<UserInfoBean, Integer> getUserInfoDao() {
        if (userInfoDao == null) {
            try {
                userInfoDao = getDao(UserInfoBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userInfoDao;
    }

    public Dao<AptitudeAllBean, Integer> getAptitudeAllDao() {
        if (aptitudeAllDao == null) {
            try {
                aptitudeAllDao = getDao(AptitudeAllBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aptitudeAllDao;
    }

    public Dao<AptitudeSelectedBean, Integer> getSelectedBeanDao() {
        if (selectedBeanDao == null) {
            try {
                selectedBeanDao = getDao(AptitudeSelectedBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return selectedBeanDao;
    }

    public Dao<EnterpriseQueryBean, Integer> getEnterpriseQueryDao() {
        if (enterpriseQueryDao == null) {
            try {
                enterpriseQueryDao = getDao(EnterpriseQueryBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enterpriseQueryDao;
    }

    public Dao<EnterprisePerformanceSettingBean, Integer> getEnterprisePerformanceSettingDao() {
        if (enterprisePerformanceSettingDao == null) {
            try {
                enterprisePerformanceSettingDao = getDao(EnterprisePerformanceSettingBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enterprisePerformanceSettingDao;
    }

    public Dao<PersonalRegisterBean, Integer> getPersonalRegisterDao() {
        if (personalRegisterDao == null) {
            try {
                personalRegisterDao = getDao(PersonalRegisterBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personalRegisterDao;
    }

    public Dao<PersonalTitleBean, Integer> getPersonalTitleDao() {
        if (personalTitleDao == null) {
            try {
                personalTitleDao = getDao(PersonalTitleBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personalTitleDao;
    }

    public Dao<PersonalManagerBean, Integer> getPersonalManagerDao() {
        if (personalManagerDao == null) {
            try {
                personalManagerDao = getDao(PersonalManagerBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personalManagerDao;
    }

    public Dao<PurposeAllBean, Integer> getPurposeAllDao() {
        if (purposeAllDao == null) {
            try {
                purposeAllDao = getDao(PurposeAllBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return purposeAllDao;
    }

    public Dao<PersonalAllBean, Integer> getPersonalAllDao() {
        if (personalAllDao == null) {
            try {
                personalAllDao = getDao(PersonalAllBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personalAllDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
    }
}
