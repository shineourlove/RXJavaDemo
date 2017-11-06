package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/17.
 */
@DatabaseTable(tableName = "aptitude_all")
public class AptitudeAllBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public AptitudeAllBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AptitudeAllBean(String aptitude_type, String aptitude_name, int aptitude_id, String aptitude_Grade, String type_id, String initial, int all_id) {
        this.aptitude_type = aptitude_type;
        this.aptitude_name = aptitude_name;
        this.aptitude_id = aptitude_id;
        this.aptitude_Grade = aptitude_Grade;
        this.type_id = type_id;
        this.initial = initial;
        this.all_id = all_id;
    }

    public String getAptitude_type() {
        return aptitude_type;
    }

    public void setAptitude_type(String aptitude_type) {
        this.aptitude_type = aptitude_type;
    }

    public String getAptitude_name() {
        return aptitude_name;
    }

    public void setAptitude_name(String aptitude_name) {
        this.aptitude_name = aptitude_name;
    }

    public int getAptitude_id() {
        return aptitude_id;
    }

    public void setAptitude_id(int aptitude_id) {
        this.aptitude_id = aptitude_id;
    }

    public String getAptitude_Grade() {
        return aptitude_Grade;
    }

    public void setAptitude_Grade(String aptitude_Grade) {
        this.aptitude_Grade = aptitude_Grade;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getAll_id() {
        return all_id;
    }

    public void setAll_id(int all_id) {
        this.all_id = all_id;
    }

    @DatabaseField(columnName = "aptitude_type")
    private String aptitude_type;
    @DatabaseField(columnName = "aptitude_name")
    private String aptitude_name;
    @DatabaseField(columnName = "aptitude_id")
    private int aptitude_id;
    @DatabaseField(columnName = "aptitude_Grade")
    private String aptitude_Grade;
    @DatabaseField(columnName = "type_id")
    private String type_id;
    @DatabaseField(columnName = "initial")
    private String initial;
    @DatabaseField(columnName = "all_id")
    private int all_id;

    @Override
    public String toString() {
        return "AptitudeAllBean{" +
                "id=" + id +
                ", aptitude_type='" + aptitude_type + '\'' +
                ", aptitude_name='" + aptitude_name + '\'' +
                ", aptitude_id=" + aptitude_id +
                ", aptitude_Grade='" + aptitude_Grade + '\'' +
                ", type_id='" + type_id + '\'' +
                ", initial='" + initial + '\'' +
                ", all_id=" + all_id +
                '}';
    }
}
