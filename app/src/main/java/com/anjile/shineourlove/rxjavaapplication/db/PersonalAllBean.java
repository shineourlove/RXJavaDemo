package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/17.
 */
@DatabaseTable(tableName = "personal_all")
public class PersonalAllBean implements Serializable {

    public PersonalAllBean() {
    }

    public PersonalAllBean(String type_name, String initial, int type_id, int id, String clock, String major_name, String major_grade, int major_id) {
        this.type_name = type_name;
        this.initial = initial;
        this.type_id = type_id;
        this.id = id;
        this.clock = clock;
        this.major_name = major_name;
        this.major_grade = major_grade;
        this.major_id = major_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getMajor_grade() {
        return major_grade;
    }

    public void setMajor_grade(String major_grade) {
        this.major_grade = major_grade;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    @DatabaseField(columnName = "type_name")
    private String type_name;
    @DatabaseField(columnName = "initial")
    private String initial;
    @DatabaseField(columnName = "type_id")
    private int type_id;
    @DatabaseField(columnName = "id")
    private int id;
    @DatabaseField(columnName = "clock")
    private String clock;
    @DatabaseField(columnName = "major_name")
    private String major_name;
    @DatabaseField(columnName = "major_grade")
    private String major_grade;
    @DatabaseField(columnName = "major_id")
    private int major_id;
}
