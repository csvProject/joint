package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.CsvExportInDto;
import com.ewiderbuy.produce.entity.ProductCondiInDto;
import com.ewiderbuy.produce.entity.ProductDto;
import com.ewiderbuy.produce.service.ProductCsvService;
import com.ewiderbuy.produce.entity.ProductCondiInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/productcsv")
public class ProductCsvController {
    @Autowired
    private ProductCsvService productCsvService;

    //根据条件查询
    @RequestMapping(value = "/findListbycondi", method = RequestMethod.POST)
    public Result findProductByCondi(@RequestBody ProductCondiInDto indto) {

        Map<String, Object> map = new HashMap<String, Object>();
        map = productCsvService.findProductByCondi(indto);
        List<ProductDto> list = (List<ProductDto>)map.get("list");
        int count = (int)map.get("count");
        return ResultUtil.success(list,count) ;
    }

    //根据条件查询(无分页)
    @RequestMapping(value = "/findListbycondinopage", method = RequestMethod.POST)
    public Result findListbycondinopage(@RequestBody ProductCondiInDto indto) {

        Map<String, Object> map = new HashMap<String, Object>();
        map = productCsvService.findProductByCondiNoPage(indto);
        List<ProductDto> list = (List<ProductDto>)map.get("list");
        int count = (int)map.get("count");
        return ResultUtil.success(list,count) ;
    }


    //根据主key条件查询
    @RequestMapping(value = "/findListbyid", method = RequestMethod.GET)
    public Result findProductById(@RequestParam(value = "productid", required = true) int productId) {

        return ResultUtil.success(productCsvService.findProductById(productId)) ;
    }

    //导出csv
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public Result exportProductCsv(@RequestBody CsvExportInDto indto) {

        return ResultUtil.success(productCsvService.exportProductCsv(indto)) ;
    }
}