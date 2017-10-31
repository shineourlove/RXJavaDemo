package com.anjile.shineourlove.rxjavaapplication.entity;

/**
 * Created by Administrator on 2017/10/31.
 * 资质查询的列表实体类
 */

public class AptitudeQueryItemEntity {
    private String name;
    private int level;
    private String area;
    private boolean authentication;

    public AptitudeQueryItemEntity() {
    }

    public AptitudeQueryItemEntity(String name, int level, String area, boolean authentication) {
        this.name = name;
        this.level = level;
        this.area = area;
        this.authentication = authentication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }
}
