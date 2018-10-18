package com.csv.java.entity;

//同步错误数据记录表dto
public class GenerateErrorDto extends BaseDto{
    //错误实例ID
    private int generateErrorId;
    //网站订单号
    private String websiteOrderNo;
    //网站id
    private int websiteId;
    //错误信息
    private String errorInfo;
    //更新时间
    private String updtTs;

    public int getGenerateErrorId() {
        return generateErrorId;
    }

    public void setGenerateErrorId(int generateErrorId) {
        this.generateErrorId = generateErrorId;
    }

    public String getWebsiteOrderNo() {
        return websiteOrderNo;
    }

    public void setWebsiteOrderNo(String websiteOrderNo) {
        this.websiteOrderNo = websiteOrderNo;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getUpdtTs() {
        return updtTs;
    }

    public void setUpdtTs(String updtTs) {
        this.updtTs = updtTs;
    }
}
