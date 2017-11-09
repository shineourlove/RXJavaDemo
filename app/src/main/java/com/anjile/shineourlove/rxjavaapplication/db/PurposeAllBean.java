package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/17.
 */
@DatabaseTable(tableName = "purpose_all")
public class PurposeAllBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public PurposeAllBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PurposeAllBean(String project_purpose, int purpose_id, String clock, boolean check) {
        this.project_purpose = project_purpose;
        this.purpose_id = purpose_id;
        this.clock = clock;
        this.check = check;
    }

    public String getProject_purpose() {
        return project_purpose;
    }

    public void setProject_purpose(String project_purpose) {
        this.project_purpose = project_purpose;
    }

    public int getPurpose_id() {
        return purpose_id;
    }

    public void setPurpose_id(int purpose_id) {
        this.purpose_id = purpose_id;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @DatabaseField(columnName = "project_purpose")
    private String project_purpose;
    @DatabaseField(columnName = "purpose_id")
    private int purpose_id;
    @DatabaseField(columnName = "clock")
    private String clock;
    @DatabaseField(columnName = "check")
    private boolean check;
}
