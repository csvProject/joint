package com.csv.java.service.impl;





import com.csv.java.dao.ProductDao;
import com.csv.java.entity.ProductCsvDto;
import com.csv.java.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "productCsvService")
public class ProductCsvServiceImpl implements ProductCsvService {

    @Autowired
    private ProductDao productDao;


    public List<ProductCsvDto> findProductByCondi(ProductCsvDto indto){
        return productDao.findProductByCondi(indto);
    }

    public ProductCsvDto findProductById(int productId){
        return productDao.findProductById(productId);
    }

}
