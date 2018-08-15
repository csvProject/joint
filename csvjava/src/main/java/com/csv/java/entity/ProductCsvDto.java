package com.csv.java.entity;

import java.util.Date;

//商品dto
public class ProductCsvDto extends BaseDto{
    //商品ID
    private int productId;
    //商品sku
    private String sku;
    //商品组套标志
    private int isSet;
    //商品分类ID
    private int ptypeId;
    //供应商ID
    private  int sId;

    private int ptypeMark;
    //采购链接
    private String cgLink;
    //组套sku
    private String skus;
    //出厂编号
    private String csbh;
    //成本
    private  double basePrice;
    //重量
    private double weight;
    //中文名
    private  String pmCn;
    //备注
    private String memo;
    // 淘宝售价
    private double cnlowSalesprice;
    //中文组套
    private String setCn;
    //中文属性1
    private String attCn1;
    //中文属性2
    private String attCn2;
    //中文属性3
    private String attCn3;
    //中文属性4
    private String attCn4;
    //日文名
    private String pmJp;
    //日元最低参考价
    private double rblowSalesPrice;
    //日文组套
    private String setJp;
    //日文属性1
    private String attJp1;
    //日文属性2
    private String attJp2;
    //日文属性3
    private String attJp3;
    //日文属性4
    private String attJp4;
    //日文站URL
    private String urlJp;
    //日文站URL_KEY
    private String urlKeyJp;
    //英文名
    private String pmOm;
    // 美元最低参考价
    private double omlowSalesPrice;
    //英文组套
    private String setOm;
    //英文属性1
    private String attOm1;
    //英文属性2
    private String attOm2;
    //英文属性3
    private String attOm3;
    //英文属性4
    private String attOm4;
    //英文站URL
    private String urlOm;
    //英文站URL_KEY
    private String urlKeyOm;
    //图片路径
    private String picUrl;
    //0：启用；1：禁用
    private int isXj;
    //启用日期
    private String sjDatetime;
    //商品分类名称
    private String ptypeNm;
    //供应商名称
    private String sNm;

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

    public int getIsSet() {
        return isSet;
    }

    public void setIsSet(int isSet) {
        this.isSet = isSet;
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

    public int getPtypeMark() {
        return ptypeMark;
    }

    public void setPtypeMark(int ptypeMark) {
        this.ptypeMark = ptypeMark;
    }

    public String getCgLink() {
        return cgLink;
    }

    public void setCgLink(String cgLink) {
        this.cgLink = cgLink;
    }

    public String getSkus() {
        return skus;
    }

    public void setSkus(String skus) {
        this.skus = skus;
    }

    public String getCsbh() {
        return csbh;
    }

    public void setCsbh(String csbh) {
        this.csbh = csbh;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPmCn() {
        return pmCn;
    }

    public void setPmCn(String pmCn) {
        this.pmCn = pmCn;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getCnlowSalesprice() {
        return cnlowSalesprice;
    }

    public void setCnlowSalesprice(double cnlowSalesprice) {
        this.cnlowSalesprice = cnlowSalesprice;
    }

    public String getSetCn() {
        return setCn;
    }

    public void setSetCn(String setCn) {
        this.setCn = setCn;
    }

    public String getAttCn1() {
        return attCn1;
    }

    public void setAttCn1(String attCn1) {
        this.attCn1 = attCn1;
    }

    public String getAttCn2() {
        return attCn2;
    }

    public void setAttCn2(String attCn2) {
        this.attCn2 = attCn2;
    }

    public String getAttCn3() {
        return attCn3;
    }

    public void setAttCn3(String attCn3) {
        this.attCn3 = attCn3;
    }

    public String getAttCn4() {
        return attCn4;
    }

    public void setAttCn4(String attCn4) {
        this.attCn4 = attCn4;
    }

    public String getPmJp() {
        return pmJp;
    }

    public void setPmJp(String pmJp) {
        this.pmJp = pmJp;
    }

    public double getRblowSalesPrice() {
        return rblowSalesPrice;
    }

    public void setRblowSalesPrice(double rblowSalesPrice) {
        this.rblowSalesPrice = rblowSalesPrice;
    }

    public String getSetJp() {
        return setJp;
    }

    public void setSetJp(String setJp) {
        this.setJp = setJp;
    }

    public String getAttJp1() {
        return attJp1;
    }

    public void setAttJp1(String attJp1) {
        this.attJp1 = attJp1;
    }

    public String getAttJp2() {
        return attJp2;
    }

    public void setAttJp2(String attJp2) {
        this.attJp2 = attJp2;
    }

    public String getAttJp3() {
        return attJp3;
    }

    public void setAttJp3(String attJp3) {
        this.attJp3 = attJp3;
    }

    public String getAttJp4() {
        return attJp4;
    }

    public void setAttJp4(String attJp4) {
        this.attJp4 = attJp4;
    }

    public String getUrlJp() {
        return urlJp;
    }

    public void setUrlJp(String urlJp) {
        this.urlJp = urlJp;
    }

    public String getUrlKeyJp() {
        return urlKeyJp;
    }

    public void setUrlKeyJp(String urlKeyJp) {
        this.urlKeyJp = urlKeyJp;
    }

    public String getPmOm() {
        return pmOm;
    }

    public void setPmOm(String pmOm) {
        this.pmOm = pmOm;
    }

    public double getOmlowSalesPrice() {
        return omlowSalesPrice;
    }

    public void setOmlowSalesPrice(double omlowSalesPrice) {
        this.omlowSalesPrice = omlowSalesPrice;
    }

    public String getSetOm() {
        return setOm;
    }

    public void setSetOm(String setOm) {
        this.setOm = setOm;
    }

    public String getAttOm1() {
        return attOm1;
    }

    public void setAttOm1(String attOm1) {
        this.attOm1 = attOm1;
    }

    public String getAttOm2() {
        return attOm2;
    }

    public void setAttOm2(String attOm2) {
        this.attOm2 = attOm2;
    }

    public String getAttOm3() {
        return attOm3;
    }

    public void setAttOm3(String attOm3) {
        this.attOm3 = attOm3;
    }

    public String getAttOm4() {
        return attOm4;
    }

    public void setAttOm4(String attOm4) {
        this.attOm4 = attOm4;
    }

    public String getUrlOm() {
        return urlOm;
    }

    public void setUrlOm(String urlOm) {
        this.urlOm = urlOm;
    }

    public String getUrlKeyOm() {
        return urlKeyOm;
    }

    public void setUrlKeyOm(String urlKeyOm) {
        this.urlKeyOm = urlKeyOm;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getIsXj() {
        return isXj;
    }

    public void setIsXj(int isXj) {
        this.isXj = isXj;
    }

    public String getSjDatetime() {
        return sjDatetime;
    }

    public void setSjDatetime(String sjDatetime) {
        this.sjDatetime = sjDatetime;
    }

    public String getPtypeNm() {
        return ptypeNm;
    }

    public void setPtypeNm(String ptypeNm) {
        this.ptypeNm = ptypeNm;
    }

    public String getsNm() {
        return sNm;
    }

    public void setsNm(String sNm) {
        this.sNm = sNm;
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
