package com.ewiderbuy.produce.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class BaseDto implements Serializable,Cloneable {
    private static final long serialVersionUID = -7534367013413101701L;

    //登录用户id
    private int logId;

    //更新人
    private int updtBy;

    //开始页数
    private int pageStart;

    //每页行数
    private int pageSize;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUpdtBy() {
        return updtBy;
    }

    public void setUpdtBy(int updtBy) {
        this.updtBy = updtBy;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }



    @Override
    public Object clone() {
        Object o = null;
        try {
            o =  super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return o;
    }
}
