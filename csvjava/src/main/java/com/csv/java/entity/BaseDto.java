package com.csv.java.entity;

import java.io.Serializable;

public class BaseDto implements Serializable {
    private static final long serialVersionUID = -7534367013413101701L;

    //登录用户id
    private int logId;

    //更新人
    private int updtBy;

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
}
