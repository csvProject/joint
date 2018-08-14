package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.ProductDto;
import com.csv.java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //根据条件查询
    @RequestMapping(value = "/findbycondi", method = RequestMethod.POST)
    public Result findProductByCondi(@RequestBody ProductDto indto) {
        System.out.println("开始查询...");
        return ResultUtil.success(productService.findProductByCondi(indto)) ;
    }

    //根据主key条件查询
    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public Result findProductById(@RequestParam(value = "productid", required = true) int productId) {
        System.out.println("开始查询...");
        return ResultUtil.success(productService.findProductById(productId)) ;
    }

}