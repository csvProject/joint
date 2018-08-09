package com.csv.java.entity;

import java.io.Serializable;

public class BaseDto implements Serializable {
    private static final long serialVersionUID = -7534367013413101701L;

    //登录用户id
    private Long logId;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
