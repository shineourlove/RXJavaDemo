package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by shineourlove on 2017/11/3.
 * 用户基础信息类
 */
@DatabaseTable(tableName = "enterprise_performance_setting")
public class EnterprisePerformanceSettingBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public EnterprisePerformanceSettingBean() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnterprisePerformanceSettingBean(String start, String end, String use, int number, int scale, String unit, int save) {
        this.start = start;
        this.end = end;
        this.use = use;
        this.number = number;
        this.scale = scale;
        this.unit = unit;
        this.save = save;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    @DatabaseField(columnName = "start")
    private String start;
    @DatabaseField(columnName = "end")
    private String end;
    @DatabaseField(columnName = "use")
    private String use;
    @DatabaseField(columnName = "number")
    private int number;
    @DatabaseField(columnName = "scale")
    private int scale;
    @DatabaseField(columnName = "unit")
    private String unit;
    @DatabaseField(columnName = "save")
    private int save;
}
