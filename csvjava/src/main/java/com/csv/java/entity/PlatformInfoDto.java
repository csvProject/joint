package com.csv.java.entity;

//平台dto
public class PlatformInfoDto extends BaseDto{
    //平台ID
    private int platformId;
    //平台名称
    private String platformNm;
    //平台类型
    private String platformType;
    //备注
    private String memo;

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getPlatformNm() {
        return platformNm;
    }

    public void setPlatformNm(String platformNm) {
        this.platformNm = platformNm;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "PlatformInfoDto{" +
                "platformId=" + platformId +
                ", platformNm='" + platformNm + '\'' +
                ", platformType='" + platformType + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
