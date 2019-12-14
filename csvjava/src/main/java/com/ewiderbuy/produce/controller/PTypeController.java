package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.PTypeDto;
import com.ewiderbuy.produce.service.PTypeService;
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

        return ResultUtil.success(ptypeService.findPTypeByCondi(indto)) ;
    }

    //根据code,name条件查询
    @RequestMapping(value = "/findbycodenm", method = RequestMethod.GET)
    public Result findPTypeByCodenm(@RequestParam(value = "cdnm", required = true) String cdnm) {

        return ResultUtil.success(ptypeService.findPTypeByCodenm(cdnm)) ;
    }

}