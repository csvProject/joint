package com.csv.java.service;






import com.csv.java.entity.ProductCsvDto;
import com.csv.java.entity.ProductDto;

import java.util.List;

public interface ProductCsvService {
    List<ProductDto> findProductByCondi(ProductCsvDto indto);

    ProductCsvDto findProductById(int productId);
}
