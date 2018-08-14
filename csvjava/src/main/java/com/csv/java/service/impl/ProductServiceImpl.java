package com.csv.java.service.impl;





import com.csv.java.dao.ProductDao;
import com.csv.java.entity.ProductDto;
import com.csv.java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    public List<ProductDto> findProductByCondi(ProductDto indto){
        return productDao.findProductByCondi(indto);
    }

    public ProductDto findProductById(int productId){
        return productDao.findProductById(productId);
    }

}
