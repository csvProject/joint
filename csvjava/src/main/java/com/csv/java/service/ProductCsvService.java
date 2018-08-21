package com.csv.java.service;






import com.csv.java.entity.ProductCsvIntoDto;
import com.csv.java.entity.ProductDto;

import java.util.List;

public interface ProductCsvService {
    List<ProductDto> findProductByCondi(ProductCsvIntoDto indto);

    ProductDto findProductById(int productId);
}
