package com.anjile.shineourlove.rxjavaapplication.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/13.
 */

public class ProvinceEntity {
    /**
     * name : 重庆市
     * short : 渝
     */

    private String name;
    @SerializedName("short")
    private String shortX;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortX() {
        return shortX;
    }

    public void setShortX(String shortX) {
        this.shortX = shortX;
    }
}
