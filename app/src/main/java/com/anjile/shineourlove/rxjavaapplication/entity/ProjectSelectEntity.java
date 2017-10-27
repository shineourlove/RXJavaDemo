package com.anjile.shineourlove.rxjavaapplication.entity;

/**
 * Created by Administrator on 2017/10/25.
 */

public class ProjectSelectEntity {
    private String itemName;
    private boolean isCheck;

    public ProjectSelectEntity() {
    }

    public ProjectSelectEntity(String itemName, boolean isCheck) {
        this.itemName = itemName;
        this.isCheck = isCheck;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
