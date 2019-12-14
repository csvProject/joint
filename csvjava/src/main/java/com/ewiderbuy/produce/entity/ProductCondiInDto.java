package com.ewiderbuy.produce.entity;

import java.util.Date;

//查询商品条件dto
public class ProductCondiInDto extends BaseDto{
    //商品ID
    private int productId;
    //商品sku
    private String sku;
    //商品分类ID
    private int ptypeId;
    //供应商ID
    private  int sId;
    //中文名
    private  String pmCn;
    //0：启用；1：禁用
    private int isXj;
    //时间开始条件
    private String sjStartDt;

    //时间开始条件
    private String sjEndDt;

    //sku与name条件
    private String skuName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getPtypeId() {
        return ptypeId;
    }

    public void setPtypeId(int ptypeId) {
        this.ptypeId = ptypeId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getPmCn() {
        return pmCn;
    }

    public void setPmCn(String pmCn) {
        this.pmCn = pmCn;
    }

    public int getIsXj() {
        return isXj;
    }

    public void setIsXj(int isXj) {
        this.isXj = isXj;
    }

    public String getSjStartDt() {
        return sjStartDt;
    }

    public void setSjStartDt(String sjStartDt) {
        this.sjStartDt = sjStartDt;
    }

    public String getSjEndDt() {
        return sjEndDt;
    }

    public void setSjEndDt(String sjEndDt) {
        this.sjEndDt = sjEndDt;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
}
