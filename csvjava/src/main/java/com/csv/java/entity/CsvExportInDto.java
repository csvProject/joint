package com.csv.java.entity;

import java.util.List;

//导出商品dto
public class CsvExportInDto extends BaseDto{
    //平台ID
    private int platformId;
    //平台账号ID
    private int pfaccountId;

    //选中商品LIST
    List<ProductDto> productDtoList;

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

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
