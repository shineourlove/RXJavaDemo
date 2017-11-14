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

    public UserInfoBean(String userId, String name, String gender, int age, String phone, String birthday, String interests, String vip, String city, String vipStartDate, String vipDateDue, String mail, String photo, String token) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.birthday = birthday;
        this.interests = interests;
        this.vip = vip;
        this.city = city;
        this.vipStartDate = vipStartDate;
        this.vipDateDue = vipDateDue;
        this.mail = mail;
        this.photo = photo;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVipStartDate() {
        return vipStartDate;
    }

    public void setVipStartDate(String vipStartDate) {
        this.vipStartDate = vipStartDate;
    }

    public String getVipDateDue() {
        return vipDateDue;
    }

    public void setVipDateDue(String vipDateDue) {
        this.vipDateDue = vipDateDue;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @DatabaseField(columnName = "user_id")
    private String userId;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "gender") //性别  男1，女0
    private String gender;
    @DatabaseField(columnName = "age")
    private int age;
    @DatabaseField(columnName = "phone")
    private String phone;
    @DatabaseField(columnName = "birthday")
    private String birthday;
    @DatabaseField(columnName = "interests")
    private String interests;
    @DatabaseField(columnName = "vip")  //是否为VIP,1是,0否
    private String vip;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "vip_start_date")
    private String vipStartDate;
    @DatabaseField(columnName = "vip_date_due")
    private String vipDateDue;
    @DatabaseField(columnName = "mail")
    private String mail;
    @DatabaseField(columnName = "photo") //头像url
    private String photo;
    @DatabaseField(columnName = "token")
    private String token;
}
