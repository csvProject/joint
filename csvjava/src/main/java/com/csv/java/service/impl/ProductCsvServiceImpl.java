package com.csv.java.service.impl;





import com.csv.java.dao.ProductDao;
import com.csv.java.entity.ProductCsvIntoDto;
import com.csv.java.entity.ProductDto;
import com.csv.java.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "productCsvService")
public class ProductCsvServiceImpl implements ProductCsvService {

    @Autowired
    private ProductDao productDao;


    public List<ProductDto> findProductByCondi(ProductCsvIntoDto indto){
        return productDao.findProductByCondi(indto);
    }

    public ProductDto findProductById(int productId){
        return productDao.findProductById(productId);
    }

}
