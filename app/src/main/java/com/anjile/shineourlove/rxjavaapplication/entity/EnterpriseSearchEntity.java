package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by shineourlove on 2017/11/3.
 * 查询出的企业列表实体类
 */

public class EnterpriseSearchEntity {

    /**
     * data : [{"misconduct":" 无","bidding":"1","mail":"126@qq.com","postcode":"400003","sites":"无","starttime":"2012-01-24","registerType":"股份有限公司","recodetime":"","registersite":"重庆市","principal":3003,"site":"重庆市渝北区东湖北路","performance":"1,3,9,15","creditCode":2147483647,"corporate":"谭总","phone":"13888888888","faxno":"75512348","name":"重庆安吉乐建筑有限公司","aptitude":"6,7","id":107,"explains":"优质企业,诚信企业国家重点扶持单位"}]
     * msg : 企业数据获取成功
     * status : 1
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
         * misconduct :  无
         * bidding : 1
         * mail : 126@qq.com
         * postcode : 400003
         * sites : 无
         * starttime : 2012-01-24
         * registerType : 股份有限公司
         * recodetime :
         * registersite : 重庆市
         * principal : 3003
         * site : 重庆市渝北区东湖北路
         * performance : 1,3,9,15
         * creditCode : 2147483647
         * corporate : 谭总
         * phone : 13888888888
         * faxno : 75512348
         * name : 重庆安吉乐建筑有限公司
         * aptitude : 6,7
         * id : 107
         * explains : 优质企业,诚信企业国家重点扶持单位
         */

        private String misconduct;
        private String bidding;
        private String mail;
        private String postcode;
        private String sites;
        private String starttime;
        private String registerType;
        private String recodetime;
        private String registersite;
        private int principal;
        private String site;
        private String performance;
        private int creditCode;
        private String corporate;
        private String phone;
        private String faxno;
        private String name;
        private String aptitude;
        private int id;
        private String explains;

        public String getMisconduct() {
            return misconduct;
        }

        public void setMisconduct(String misconduct) {
            this.misconduct = misconduct;
        }

        public String getBidding() {
            return bidding;
        }

        public void setBidding(String bidding) {
            this.bidding = bidding;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getSites() {
            return sites;
        }

        public void setSites(String sites) {
            this.sites = sites;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getRecodetime() {
            return recodetime;
        }

        public void setRecodetime(String recodetime) {
            this.recodetime = recodetime;
        }

        public String getRegistersite() {
            return registersite;
        }

        public void setRegistersite(String registersite) {
            this.registersite = registersite;
        }

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

        public String getPerformance() {
            return performance;
        }

        public void setPerformance(String performance) {
            this.performance = performance;
        }

        public int getCreditCode() {
            return creditCode;
        }

        public void setCreditCode(int creditCode) {
            this.creditCode = creditCode;
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

        public String getFaxno() {
            return faxno;
        }

        public void setFaxno(String faxno) {
            this.faxno = faxno;
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

        public String getExplains() {
            return explains;
        }

        public void setExplains(String explains) {
            this.explains = explains;
        }
    }
}
