package com.anjile.shineourlove.rxjavaapplication.entity;

/**
 * Created by Administrator on 2017/10/12.
 */

public class CompanyDetails {
    private String name;
    private Long Date;
    private int aptitude;
    private int manage;
    private int honor;

    public CompanyDetails() {
    }

    public CompanyDetails(String name, Long date, int aptitude, int manage, int honor) {
        this.name = name;
        Date = date;
        this.aptitude = aptitude;
        this.manage = manage;
        this.honor = honor;
    }

    public int getAptitude() {
        return aptitude;
    }

    public void setAptitude(int aptitude) {
        this.aptitude = aptitude;
    }

    public int getManage() {
        return manage;
    }

    public void setManage(int manage) {
        this.manage = manage;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }
}
