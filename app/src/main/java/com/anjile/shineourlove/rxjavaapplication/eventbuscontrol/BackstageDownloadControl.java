package com.anjile.shineourlove.rxjavaapplication.eventbuscontrol;

/**
 * Created by Administrator on 2017/11/6.
 */

public class BackstageDownloadControl {
    private int type;

    public BackstageDownloadControl() {
    }

    public BackstageDownloadControl(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
