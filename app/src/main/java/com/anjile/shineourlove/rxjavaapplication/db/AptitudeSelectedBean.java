package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by shineourlove on 2017/11/3.
 * 用户基础信息类
 */
@DatabaseTable(tableName = "aptitude_selected")
public class AptitudeSelectedBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public AptitudeSelectedBean() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AptitudeSelectedBean(String name, String details, String rank) {
        this.name = name;
        this.details = details;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "details")
    private String details;
    @DatabaseField(columnName = "rank")
    private String rank;

}
