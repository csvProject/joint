package com.csv.java.entity;

import java.util.List;

//导出商品分组dto
public class ProductGroupOutDto extends BaseDto{
    //平台ID
    private int platformId;
    //平台账号ID
    private int pfaccountId;
    //商品分类ID
    private int ptypeId;
    //供应商ID
    private  int sId;

    //选中商品LIST
    List<ProductDto> productDtoList;

    //对应模板sql
    private String csvSql;

    //对应CSV文件名
    private String csvFileName;

    //是否显示头部
    private Integer headShow;

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getPfaccountId() {
        return pfaccountId;
    }

    public void setPfaccountId(int pfaccountId) {
        this.pfaccountId = pfaccountId;
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

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public String getCsvSql() {
        return csvSql;
    }

    public void setCsvSql(String csvSql) {
        this.csvSql = csvSql;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public Integer getHeadShow() {
        return headShow;
    }

    public void setHeadShow(Integer headShow) {
        this.headShow = headShow;
    }
}
