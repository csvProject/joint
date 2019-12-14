package com.ewiderbuy.produce.service;

import com.ewiderbuy.produce.entity.CsvExportInDto;
import com.ewiderbuy.produce.entity.ProductBatDto;
import com.ewiderbuy.produce.entity.ProductCondiInDto;
import com.ewiderbuy.produce.entity.ProductDto;
import com.ewiderbuy.produce.entity.ProductCondiInDto;
import com.ewiderbuy.produce.entity.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductCsvService {
    Map<String, Object> findProductByCondi(ProductCondiInDto indto);

    Map<String, Object> findProductByCondiNoPage(ProductCondiInDto indto);

    ProductDto findProductById(int productId);

    Map<String,Object> exportProductCsv(CsvExportInDto csvExportInDto );

}
