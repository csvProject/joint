package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.SysCodeDto;
import com.ewiderbuy.produce.service.SysCodeService;
import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.entity.SysCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/syscode")
public class SysCodeController {
    @Autowired
    private SysCodeService sysCodeService;

    //根据分类code查询
    @RequestMapping(value = "/findbytypecd", method = RequestMethod.GET)
    public Result findSysCodeByTypeCd(@RequestParam(value = "typecd", required = true) int typeCd) {

        return ResultUtil.success(sysCodeService.findSysCodeByTypeCd(typeCd)) ;
    }

    //根据分类code、代码code查询
    @RequestMapping(value = "/findbysyscd", method = RequestMethod.POST)
    public Result findSysCodeBySysCd(@RequestBody SysCodeDto indto) {

        return ResultUtil.success(sysCodeService.findSysCodeBySysCd(indto)) ;
    }

}