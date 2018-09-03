package com.csv.java.entity;

import java.util.List;

//商品dto
public class ProductBatDto extends BaseDto {
    private List<ProductDto> productDtoList;

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
