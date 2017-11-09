package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class PurposeEntity {
    /**
     * msg : 查询业绩工程用途成功!
     * status : 1
     * data : [{"project_purpose":"园林工程","id":1,"clock":1510108278824},{"project_purpose":"给水排水","id":2,"clock":1510108278824},{"project_purpose":"工业建筑","id":3,"clock":1510108278824},{"project_purpose":"公共建筑","id":4,"clock":1510108278824},{"project_purpose":"交通运输","id":5,"clock":1510108278824},{"project_purpose":"商用建筑","id":6,"clock":1510108278824},{"project_purpose":"桥梁隧道","id":7,"clock":1510108278824},{"project_purpose":"热力工程","id":8,"clock":1510108278824},{"project_purpose":"道路工程","id":9,"clock":1510108278824},{"project_purpose":"其他工程","id":10,"clock":1510108278824}]
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
         * project_purpose : 园林工程
         * id : 1
         * clock : 1510108278824
         */

        private String project_purpose;
        private int id;
        private long clock;

        public String getProject_purpose() {
            return project_purpose;
        }

        public void setProject_purpose(String project_purpose) {
            this.project_purpose = project_purpose;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getClock() {
            return clock;
        }

        public void setClock(long clock) {
            this.clock = clock;
        }
    }
}
