package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.SupplierDto;
import com.ewiderbuy.produce.service.SupplierService;
import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.entity.SupplierDto;
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