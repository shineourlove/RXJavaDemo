package com.anjile.shineourlove.rxjavaapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */

public class CheckSecurityCodeBean {

    /**
     * msg : 验证成功!
     * status : 1
     * token : e2F1dGhDb2RlPTk5OTk5OSwgcGhvbmU9MTM2Mzc4OTcyNTYsIHByb29mX3RpbWU9V2VkIE5vdiAx
     * NCAxNTowNToyNSBDU1QgMjAxOH0=.MTUxMDY0MzQxMTgzMw==
     * data : [{"birthday":"","vipStartDate":"","gender":"","mail":"","phone":"13637897256","vipDateDue":"","name":"13637897256","photo":"","id":5,"interests":"","vip":"","age":""}]
     */

    private String msg;
    private int status;
    private String token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * birthday :
         * vipStartDate :
         * gender :
         * mail :
         * phone : 13637897256
         * vipDateDue :
         * name : 13637897256
         * photo :
         * id : 5
         * interests :
         * vip :
         * age :
         */

        private String birthday;
        private String vipStartDate;
        private String gender;
        private String mail;
        private String phone;
        private String vipDateDue;  //"yyyy-MM-dd HH:mm:ss"
        private String name;
        private String photo;
        private int id;
        private String interests;
        private String vip;
        private String age;
        private String city;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getVipStartDate() {
            return vipStartDate;
        }

        public void setVipStartDate(String vipStartDate) {
            this.vipStartDate = vipStartDate;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVipDateDue() {
            return vipDateDue;
        }

        public void setVipDateDue(String vipDateDue) {
            this.vipDateDue = vipDateDue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInterests() {
            return interests;
        }

        public void setInterests(String interests) {
            this.interests = interests;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
