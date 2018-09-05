package com.csv.java.service;

import com.csv.java.entity.CsvExportInDto;
import com.csv.java.entity.ProductBatDto;
import com.csv.java.entity.ProductCondiInDto;
import com.csv.java.entity.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductCsvService {
    List<ProductDto> findProductByCondi(ProductCondiInDto indto);

    ProductDto findProductById(int productId);

    Map<String,Object> exportProductCsv(CsvExportInDto csvExportInDto );

}
