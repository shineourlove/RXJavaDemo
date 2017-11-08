package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by shineourlove on 2017/11/3.
 * 用户基础信息类
 */
@DatabaseTable(tableName = "personal_register")
public class PersonalRegisterBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public PersonalRegisterBean() {
    }

    public PersonalRegisterBean(String name, String rank, String number, String details) {
        this.name = name;
        this.rank = rank;
        this.number = number;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "rank")
    private String rank;
    @DatabaseField(columnName = "number")
    private String number;
    @DatabaseField(columnName = "details")
    private String details;
}
