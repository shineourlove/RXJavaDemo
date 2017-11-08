package com.anjile.shineourlove.rxjavaapplication.eventbuscontrol;

/**
 * Created by Administrator on 2017/11/8.
 * 用于在选择资质返回后通知QueryActivity刷新页面
 */

public class AptitudeBackControl {
    private int type;

    public AptitudeBackControl(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
