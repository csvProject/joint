package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.CsvTemplateInfoDto;
import com.csv.java.service.CsvTemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/csvtempinfo")
public class CsvTemplateInfoController {
    @Autowired
    private CsvTemplateInfoService csvTemplateInfoService;

    //根据模板ID查询单件
    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public Result findCsvTempInfoById(@RequestParam(value = "csvtempid", required = true) int csvtempId) {
        System.out.println("开始查询...");
        return ResultUtil.success(csvTemplateInfoService.findCsvTempInfoById(csvtempId));
    }

    //根据条件查询
    @RequestMapping(value = "/findbycondi", method = RequestMethod.POST)
    public Result findCsvTempInfoByCondi(@RequestBody CsvTemplateInfoDto indto) {
        System.out.println("开始查询...");
        return ResultUtil.success(csvTemplateInfoService.findCsvTempInfoByCondi(indto)) ;
    }

    /**
     * 添加模板
     * REST Client post Content-Type:application/json;charset=UTF-8  Text :
     *{"platformId":1,"pfaccountId":3,"ptypeId":1,"sId":1,"csvtempNm":"测试模板1","lowExpr":"lowExpr1","gdExpr":"gdExpr1","origiExpr":"origiExpr1","memo":"112","logId":1}
     * @author wkm
     * @since 2018/8/9
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertCsvTempInfo(@RequestBody CsvTemplateInfoDto indto) {
        System.out.println("开始添加..."+indto.toString());
        csvTemplateInfoService.insertCsvTempInfo(indto);
        return ResultUtil.success(null) ;
    }

    //根据ID删除
    @RequestMapping(value = "/delbyid", method = RequestMethod.GET)
    public Result delPlatformInfoByPfid(@RequestParam(value = "csvtempid", required = true) int csvtempId) {
        System.out.println("开始删除...");
        csvTemplateInfoService.delCsvTempInfoById(csvtempId); ;
        return ResultUtil.success(null) ;
    }

    //根据ID更新
    @RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
    public Result updCsvTempInfoById(@RequestBody CsvTemplateInfoDto indto) {
        System.out.println("开始更新..."+indto.toString());
        csvTemplateInfoService.updCsvTempInfoById(indto);
        return ResultUtil.success(null) ;
    }

}