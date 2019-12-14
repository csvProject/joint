package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.CsvTemplateInfoDto;
import com.ewiderbuy.produce.service.CsvTemplateInfoService;
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

        return ResultUtil.success(csvTemplateInfoService.findCsvTempInfoById(csvtempId));
    }

    //根据条件查询
    @RequestMapping(value = "/findbycondi", method = RequestMethod.POST)
    public Result findCsvTempInfoByCondi(@RequestBody CsvTemplateInfoDto indto) {

        return ResultUtil.success(csvTemplateInfoService.findCsvTempInfoByCondi(indto)) ;
    }

    /**
     * 添加模板
     * REST Client post Content-Type:application/json;charset=UTF-8  Text :
     *{"platformId":1,"pfaccountId":3,"ptypeId":1,"sId":1,"csvtempNm":"测试模板1","lowExpr":"lowExpr1","gdExpr":"gdExpr1","origiExpr":"origiExpr1","isUse":0,"joinBy":1,"memo":"112","logId":1}
     * @author wkm
     * @since 2018/8/9
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertCsvTempInfo(@RequestBody CsvTemplateInfoDto indto) {

        int ret = csvTemplateInfoService.insertCsvTempInfo(indto);
        if (ret == -1){
            return ResultUtil.success(ret,"该平台、账号、商品类型、供应商下模板已存在",null) ;
        }
        if (ret == -2){
            return ResultUtil.success(ret,"模板名称已存在",null) ;
        }
        if (ret == -3){
            return ResultUtil.success(ret,"自定义公式无效",null) ;
        }
        return ResultUtil.success(null);
    }

    //根据ID删除
    @RequestMapping(value = "/delbyid", method = RequestMethod.GET)
    public Result delPlatformInfoByPfid(@RequestParam(value = "csvtempid", required = true) int csvtempId) {

        csvTemplateInfoService.delCsvTempInfoById(csvtempId); ;
        return ResultUtil.success(null) ;
    }

    //根据ID更新
    @RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
    public Result updCsvTempInfoById(@RequestBody CsvTemplateInfoDto indto) {

        int ret = csvTemplateInfoService.updCsvTempInfoById(indto);
        if (ret == -2){
            return ResultUtil.success(ret,"模板名称已存在",null) ;
        }
        if (ret == -3){
            return ResultUtil.success(ret,"自定义公式无效",null) ;
        }
        return ResultUtil.success(null) ;
    }

    //
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public Result copyCsvTempInfo(@RequestBody CsvTemplateInfoDto indto) {

        int ret = csvTemplateInfoService.copyCsvTempInfo(indto);
        if (ret == -1){
            return ResultUtil.success(ret,"该平台、账号、商品类型、供应商下模板已存在",null) ;
        }
        if (ret == -2){
            return ResultUtil.success(ret,"模板名称已存在",null) ;
        }
        if (ret == -3){
            return ResultUtil.success(ret,"自定义公式无效",null) ;
        }
        return ResultUtil.success(null);
    }
}