package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.CsvTempBatDto;
import com.ewiderbuy.produce.entity.CsvTemplateDetailDto;
import com.ewiderbuy.produce.service.CsvTemplateDetailService;
import com.ewiderbuy.produce.service.CsvTemplateRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/csvtempdetail")
public class CsvTemplateDetailController {
    @Autowired
    private CsvTemplateDetailService csvTemplateDetailService;

    @Autowired
    private CsvTemplateRuleService csvTemplateRuleService;

    //根据模板ID查询所有字段
    @RequestMapping(value = "/findbycsvtempid", method = RequestMethod.GET)
    public Result findCsvTempDetailBycsvtempId(@RequestParam(value = "csvtempid", required = true) int csvtempId) {

        return ResultUtil.success(csvTemplateDetailService.findCsvTempDetailBycsvtempId(csvtempId));
    }

    //根据ID查询单件
    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public Result findCsvTempDetailById(@RequestParam(value = "csvfieldid", required = true) int csvFieldId) {

        return ResultUtil.success(csvTemplateDetailService.findCsvTempDetailById(csvFieldId));
    }

    //根据key查询
    @RequestMapping(value = "/findbykey", method = RequestMethod.POST)
    public Result findCsvTempDetailByKey(@RequestBody CsvTemplateDetailDto indto) {

        return ResultUtil.success(csvTemplateDetailService.findCsvTempDetailByKey(indto)) ;
    }

    /**
     * 添加字段
     * REST Client post Content-Type:application/json;charset=UTF-8  Text :
     *{"csvtempId":2,"fieldKey":"productno","fieldNm":"产品编号","fieldValue":"10001001","fieldType":1,"fieldSort":1,"logId":1}
     * @author wkm
     * @since 2018/8/9
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertCsvTempDetail(@RequestBody CsvTemplateDetailDto indto) {

        csvTemplateDetailService.insertCsvTempDetail(indto);
        return ResultUtil.success(null) ;
    }

    //根据ID删除
    @RequestMapping(value = "/delbyid", method = RequestMethod.GET)
    public Result delCsvTempDetailById(@RequestParam(value = "csvfieldid", required = true) int csvFieldId) {

        csvTemplateDetailService.delCsvTempDetailById(csvFieldId); ;
        return ResultUtil.success(null) ;
    }

    //根据ID更新
    @RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
    public Result updCsvTempDetailById(@RequestBody CsvTemplateDetailDto indto) {

        csvTemplateDetailService.updCsvTempDetailById(indto);

        return ResultUtil.success(null) ;
    }

    //批量更新模板字段
    @RequestMapping(value = "/updatedetailbat", method = RequestMethod.POST)
    public Result updCsvTempDetail(@RequestBody CsvTempBatDto indto) {

        csvTemplateDetailService.updCsvTempDetailBat(indto);

        return ResultUtil.success(null) ;
    }
}