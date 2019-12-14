package com.ewiderbuy.produce.entity;

import java.sql.Timestamp;
//平台账号indto
public class PfaccountDto extends BaseDto{
    //平台账号ID
    private int pfaccountId;
    //账号名
    private String pfaccountNm;
    //平台ID
    private int platformId;
    //备注
    private String memo;

    public int getPfaccountId() {
        return pfaccountId;
    }

    public void setPfaccountId(int pfaccountId) {
        this.pfaccountId = pfaccountId;
    }

    public String getPfaccountNm() {
        return pfaccountNm;
    }

    public void setPfaccountNm(String pfaccountNm) {
        this.pfaccountNm = pfaccountNm;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
