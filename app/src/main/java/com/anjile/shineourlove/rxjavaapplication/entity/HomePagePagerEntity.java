package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class HomePagePagerEntity {
    /**
     * msg : 获取轮播成功
     * status : 1
     * data : [{"activities_url":"https://www.baidu.com","picture_url":"http://192.168.1.167:8080/architecture/File/img/carousel/123.png","id":1,"type":1},{"activities_url":"https://www.baidu.com","picture_url":"http://192.168.1.167:8080/architecture/File/img/carousel/456.png","id":2,"type":1},{"activities_url":"https://www.baidu.com","picture_url":"http://192.168.1.167:8080/architecture/File/img/carousel/234.png","id":3,"type":1}]
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
         * activities_url : https://www.baidu.com
         * picture_url : http://192.168.1.167:8080/architecture/File/img/carousel/123.png
         * id : 1
         * type : 1
         */

        private String activities_url;
        private String picture_url;
        private int id;
        private int type;

        public String getActivities_url() {
            return activities_url;
        }

        public void setActivities_url(String activities_url) {
            this.activities_url = activities_url;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
