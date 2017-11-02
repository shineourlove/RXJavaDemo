package com.anjile.shineourlove.rxjavaapplication.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/17.
 */
@DatabaseTable(tableName = "work_resume")
public class WorkResumeBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;

    public WorkResumeBean() {
    }

    public WorkResumeBean(String company, String department, String position, String start, String end, String content) {
        this.company = company;
        this.department = department;
        this.position = position;
        this.start = start;
        this.end = end;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @DatabaseField(columnName = "company")
    private String company;
    @DatabaseField(columnName = "department")
    private String department;
    @DatabaseField(columnName = "position")
    private String position;
    @DatabaseField(columnName = "start")
    private String start;
    @DatabaseField(columnName = "end")
    private String end;
    @DatabaseField(columnName = "content")
    private String content;
}
