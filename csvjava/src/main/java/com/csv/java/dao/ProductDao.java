package com.csv.java.dao;


import com.csv.java.entity.ProductCsvDto;

import java.util.List;

public interface ProductDao {
    //多条件查询
    List<ProductCsvDto> findProductByCondi(ProductCsvDto indto);

    //主Key查询
    ProductCsvDto findProductById(int productId);

}
