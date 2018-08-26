package com.csv.java.dao;


import com.csv.java.entity.ProductCondiInDto;
import com.csv.java.entity.ProductDto;

import java.util.List;

public interface ProductDao {
    //多条件查询
    List<ProductDto> findProductByCondi(ProductCondiInDto indto);

    //主Key查询
    ProductDto findProductById(int productId);

}
