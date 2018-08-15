package com.csv.java.service;






import com.csv.java.entity.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findProductByCondi(ProductDto indto);

    ProductDto findProductById(int productId);
}
