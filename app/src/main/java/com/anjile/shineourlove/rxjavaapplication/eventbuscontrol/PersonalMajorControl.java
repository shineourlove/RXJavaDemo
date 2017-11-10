package com.anjile.shineourlove.rxjavaapplication.eventbuscontrol;

import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllBean;

/**
 * Created by Administrator on 2017/11/10.
 * 人员选择信息传递类
 */

public class PersonalMajorControl {
    /**
     * type :类型 0:人员专业选择 1:人员专业等级选择
     */
    private PersonalAllBean allBean;
    private int type;
    private int position;

    public PersonalMajorControl(PersonalAllBean allBean, int type, int position) {
        this.allBean = allBean;
        this.type = type;
        this.position = position;
    }

    public PersonalAllBean getAllBean() {
        return allBean;
    }

    public void setAllBean(PersonalAllBean allBean) {
        this.allBean = allBean;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
