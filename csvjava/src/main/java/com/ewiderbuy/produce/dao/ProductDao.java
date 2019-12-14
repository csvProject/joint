package com.ewiderbuy.produce.dao;


import com.ewiderbuy.produce.entity.ProductCondiInDto;
import com.ewiderbuy.produce.entity.ProductDto;
import com.ewiderbuy.produce.entity.ProductCondiInDto;
import com.ewiderbuy.produce.entity.ProductDto;

import java.util.List;

public interface ProductDao {
    //多条件查询
    List<ProductDto> findProductByCondi(ProductCondiInDto indto);

    //主Key查询
    ProductDto findProductById(int productId);

}
