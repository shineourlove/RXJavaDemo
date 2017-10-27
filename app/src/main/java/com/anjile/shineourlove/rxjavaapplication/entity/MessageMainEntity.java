package com.anjile.shineourlove.rxjavaapplication.entity;

/**
 * Created by Administrator on 2017/10/27.
 */

public class MessageMainEntity {
    private boolean isPoint;
    private String imgURL;
    private String title;
    private String content;
    private long date;

    public MessageMainEntity() {
    }

    public MessageMainEntity(boolean isPoint, String imgURL, String title, String content, long date) {
        this.isPoint = isPoint;
        this.imgURL = imgURL;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public boolean isPoint() {
        return isPoint;
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
