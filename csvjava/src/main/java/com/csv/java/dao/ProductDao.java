package com.csv.java.dao;


import com.csv.java.entity.ProductCsvIntoDto;
import com.csv.java.entity.ProductDto;

import java.util.List;

public interface ProductDao {
    //多条件查询
    List<ProductDto> findProductByCondi(ProductCsvIntoDto indto);

    //主Key查询
    ProductDto findProductById(int productId);

}
