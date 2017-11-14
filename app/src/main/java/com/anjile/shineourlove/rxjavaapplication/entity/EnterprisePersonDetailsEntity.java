package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class EnterprisePersonDetailsEntity {
    /**
     * msg : 查询企业业绩列表成功!
     * status : 1
     * data : [{"type_name":"注册类","code":"渝201131339033","join_date":"2015-02-02","name":"张三","id":1,"major_name":"港口与航道建造师","major_grade":"一级"},{"type_name":"注册类","code":"渝201458784121","join_date":"2015-02-02","name":"李四","id":2,"major_name":"港口与航道建造师","major_grade":"一级"},{"type_name":"注册类","code":"渝201458788888","join_date":"2016-01-01","name":"张三","id":1,"major_name":"港口与航道建造师","major_grade":"二级"},{"type_name":"注册类","code":"渝201458789999","join_date":"2016-01-01","name":"王五","id":3,"major_name":"公路建造师","major_grade":"一级"}]
     */

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
         * type_name : 注册类
         * code : 渝201131339033
         * join_date : 2015-02-02
         * name : 张三
         * id : 1
         * major_name : 港口与航道建造师
         * major_grade : 一级
         */

        private String type_name;
        private String code;
        private String join_date;
        private String name;
        private int id;
        private String major_name;
        private String major_grade;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getJoin_date() {
            return join_date;
        }

        public void setJoin_date(String join_date) {
            this.join_date = join_date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMajor_name() {
            return major_name;
        }

        public void setMajor_name(String major_name) {
            this.major_name = major_name;
        }

        public String getMajor_grade() {
            return major_grade;
        }

        public void setMajor_grade(String major_grade) {
            this.major_grade = major_grade;
        }
    }
}
