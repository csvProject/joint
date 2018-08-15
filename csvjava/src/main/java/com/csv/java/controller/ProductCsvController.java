package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.ProductCsvDto;
import com.csv.java.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/productcsv")
public class ProductCsvController {
    @Autowired
    private ProductCsvService productCsvService;

    //根据条件查询
    @RequestMapping(value = "/findListbycondi", method = RequestMethod.POST)
    public Result findProductByCondi(@RequestBody ProductCsvDto indto) {
        System.out.println("开始查询...");
        return ResultUtil.success(productCsvService.findProductByCondi(indto)) ;
    }

    //根据主key条件查询
    @RequestMapping(value = "/findListbyid", method = RequestMethod.GET)
    public Result findProductById(@RequestParam(value = "productid", required = true) int productId) {
        System.out.println("开始查询...");
        return ResultUtil.success(productCsvService.findProductById(productId)) ;
    }

}