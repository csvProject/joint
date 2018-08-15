package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.PTypeDto;
import com.csv.java.service.PTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ptype")
public class PTypeController {
    @Autowired
    private PTypeService ptypeService;

    //根据条件查询
    @RequestMapping(value = "/findbycondi", method = RequestMethod.POST)
    public Result findPTypeByCondi(@RequestBody PTypeDto indto) {
        System.out.println("开始查询...");
        return ResultUtil.success(ptypeService.findPTypeByCondi(indto)) ;
    }

    //根据code,name条件查询
    @RequestMapping(value = "/findbycodenm", method = RequestMethod.GET)
    public Result findPTypeByCodenm(@RequestParam(value = "cdnm", required = true) String cdnm) {
        System.out.println("开始查询...");
        return ResultUtil.success(ptypeService.findPTypeByCodenm(cdnm)) ;
    }

}