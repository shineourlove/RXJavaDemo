package com.anjile.shineourlove.rxjavaapplication.entity;

import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class PersonalAllEntity {
    private String msg;
    private int status;
    private List<PersonalAllBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PersonalAllBean> getData() {
        return data;
    }

    public void setData(List<PersonalAllBean> data) {
        this.data = data;
    }
}
