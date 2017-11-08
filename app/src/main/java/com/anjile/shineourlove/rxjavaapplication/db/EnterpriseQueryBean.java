package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by shineourlove on 2017/11/3.
 * 用户基础信息类
 */
@DatabaseTable(tableName = "enterprise_query")
public class EnterpriseQueryBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public EnterpriseQueryBean() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnterpriseQueryBean(String name, String province, String legal_person, String require) {
        this.name = name;
        this.province = province;
        this.legal_person = legal_person;
        this.require = require;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "province")
    private String province;
    @DatabaseField(columnName = "legal_person")
    private String legal_person;
    @DatabaseField(columnName = "require")
    private String require;
}
