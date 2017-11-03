package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by shineourlove on 2017/11/3.
 * 用户基础信息类
 */
@DatabaseTable(tableName = "user_info")
public class UserInfoBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public UserInfoBean() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfoBean(String name, String phone, String city, String id_number, String token) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.id_number = id_number;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "phone")
    private String phone;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "id_number")
    private String id_number;
    @DatabaseField(columnName = "token")
    private String token;
}
