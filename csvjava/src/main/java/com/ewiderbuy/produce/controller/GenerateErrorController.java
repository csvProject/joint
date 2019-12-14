package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.CsvTemplateInfoDto;
import com.ewiderbuy.produce.entity.GenerateErrorDto;
import com.ewiderbuy.produce.service.CsvTemplateInfoService;
import com.ewiderbuy.produce.service.GenerateErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/generateerror")
public class GenerateErrorController {
    @Autowired
    private GenerateErrorService generateErrorService;

    //根据条件查询
    @RequestMapping(value = "/finderrorderno", method = RequestMethod.POST)
    public Result findErrOrderNo(@RequestBody GenerateErrorDto indto) {

        return ResultUtil.success(generateErrorService.findErrOrderNo(indto)) ;
    }

    //根据ID更新del_flag
    @RequestMapping(value = "/upddelflag", method = RequestMethod.GET)
    public Result updDelFlag(@RequestParam(value = "generateerrorid", required = true) int generateErrorId) {

        generateErrorService.updDelFlag(generateErrorId); ;
        return ResultUtil.success(null) ;
    }
}