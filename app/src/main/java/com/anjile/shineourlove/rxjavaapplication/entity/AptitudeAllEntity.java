package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class AptitudeAllEntity {

    private String msg;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * aptitude_type : 建筑行业
         * aptitude_name : 爆破专业单位许可证
         * aptitude_id : 1
         * aptitude_Grade : 一级
         * type_id : 1
         * initial : B
         * id : 1
         */

        private String aptitude_type;
        private String aptitude_name;
        private int aptitude_id;
        private String aptitude_Grade;
        private String type_id;
        private String initial;
        private int id;

        public String getAptitude_type() {
            return aptitude_type;
        }

        public void setAptitude_type(String aptitude_type) {
            this.aptitude_type = aptitude_type;
        }

        public String getAptitude_name() {
            return aptitude_name;
        }

        public void setAptitude_name(String aptitude_name) {
            this.aptitude_name = aptitude_name;
        }

        public int getAptitude_id() {
            return aptitude_id;
        }

        public void setAptitude_id(int aptitude_id) {
            this.aptitude_id = aptitude_id;
        }

        public String getAptitude_Grade() {
            return aptitude_Grade;
        }

        public void setAptitude_Grade(String aptitude_Grade) {
            this.aptitude_Grade = aptitude_Grade;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
