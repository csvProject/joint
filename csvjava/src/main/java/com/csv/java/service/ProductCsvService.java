package com.csv.java.service;






import com.csv.java.entity.ProductCsvDto;

import java.util.List;

public interface ProductCsvService {
    List<ProductCsvDto> findProductByCondi(ProductCsvDto indto);

    ProductCsvDto findProductById(int productId);
}
