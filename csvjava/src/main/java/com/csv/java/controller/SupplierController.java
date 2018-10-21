package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.SupplierDto;
import com.csv.java.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    //根据条件查询
    @RequestMapping(value = "/findbycondi", method = RequestMethod.POST)
    public Result findPTypeByCondi(@RequestBody SupplierDto indto) {

        return ResultUtil.success(supplierService.findSupplierByCondi(indto)) ;
    }

    //根据code,name条件查询
    @RequestMapping(value = "/findbycodenm", method = RequestMethod.GET)
    public Result findPTypeByCodenm(@RequestParam(value = "cdnm", required = true) String cdnm) {

        return ResultUtil.success(supplierService.findSupplierByCodenm(cdnm)) ;
    }

}