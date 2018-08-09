package com.csv.java.entity;

import java.sql.Timestamp;
//平台账号indto
public class PfaccountInDto extends BaseDto{
    //平台账号ID
    private Long pfaccountId;
    //账号名
    private String pfaccountNm;
    //平台ID
    private Long platformId;
    //备注
    private String memo;
    //更新人
    private Long updtBy;

    public Long getPfaccountId() {
        return pfaccountId;
    }

    public void setPfaccountId(Long pfaccountId) {
        this.pfaccountId = pfaccountId;
    }

    public String getPfaccountNm() {
        return pfaccountNm;
    }

    public void setPfaccountNm(String pfaccountNm) {
        this.pfaccountNm = pfaccountNm;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getUpdtBy() {
        return updtBy;
    }

    public void setUpdtBy(Long updtBy) {
        this.updtBy = updtBy;
    }
}
