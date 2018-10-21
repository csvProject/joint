package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.CsvTemplateInfoDto;
import com.csv.java.entity.GenerateErrorDto;
import com.csv.java.service.CsvTemplateInfoService;
import com.csv.java.service.GenerateErrorService;
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