package com.csv.java.entity;

//查询订单条件dto
public class OrderCondiInDto extends BaseDto{

    //时间开始条件
    private String dhStartDt;

    //时间开始条件
    private String dhEndDt;

    //网站订单号
    private String websiteid;

    public String getDhStartDt() {
        return dhStartDt;
    }

    public void setDhStartDt(String dhStartDt) {
        this.dhStartDt = dhStartDt;
    }

    public String getDhEndDt() {
        return dhEndDt;
    }

    public void setDhEndDt(String dhEndDt) {
        this.dhEndDt = dhEndDt;
    }

    public String getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(String websiteid) {
        this.websiteid = websiteid;
    }
}
