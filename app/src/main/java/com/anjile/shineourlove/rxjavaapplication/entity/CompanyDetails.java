package com.anjile.shineourlove.rxjavaapplication.entity;

/**
 * Created by Administrator on 2017/10/12.
 */

public class CompanyDetails {
    private String name;
    private Long Date;

    public CompanyDetails() {
    }

    public CompanyDetails(String name, Long date) {
        this.name = name;
        Date = date;
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
