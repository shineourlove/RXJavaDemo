package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class EnterpriseDetailsEntity {

    /**
     * msg : 查询企业详情成功!
     * status : 1
     * data : [{"principal":3003,"site":"重庆市渝北区东湖西路","corporate":"黄总","phone":"13777777777","name":"重庆乾和建筑有限公司","aptitude":"工程勘察专业类岩土工程（勘察）|甲级,工程设计电力行业变电工程专业|乙级,工程设计化工石化医药行业中成药专业|乙级","id":108,"starttime":"2012-01-24"}]
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
         * principal : 3003
         * site : 重庆市渝北区东湖西路
         * corporate : 黄总
         * phone : 13777777777
         * name : 重庆乾和建筑有限公司
         * aptitude : 工程勘察专业类岩土工程（勘察）|甲级,工程设计电力行业变电工程专业|乙级,工程设计化工石化医药行业中成药专业|乙级
         * id : 108
         * starttime : 2012-01-24
         */

        private int principal;
        private String site;
        private String corporate;
        private String phone;
        private String name;
        private String aptitude;
        private int id;
        private String starttime;

        public int getPrincipal() {
            return principal;
        }

        public void setPrincipal(int principal) {
            this.principal = principal;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getCorporate() {
            return corporate;
        }

        public void setCorporate(String corporate) {
            this.corporate = corporate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAptitude() {
            return aptitude;
        }

        public void setAptitude(String aptitude) {
            this.aptitude = aptitude;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }
    }
}
