package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.CsvTempBatDto;
import com.csv.java.entity.CsvTemplateDetailDto;
import com.csv.java.service.CsvTemplateDetailService;
import com.csv.java.service.CsvTemplateRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/csvtempdetail")
public class CsvTemplateDetailController {
    @Autowired
    private CsvTemplateDetailService csvTemplateDetailService;

    @Autowired
    private CsvTemplateRuleService csvTemplateRuleService;
    //根据ID查询单件
    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public Result findCsvTempDetailById(@RequestParam(value = "csvfieldid", required = true) int csvFieldId) {
        System.out.println("开始查询...");
        return ResultUtil.success(csvTemplateDetailService.findCsvTempDetailById(csvFieldId));
    }

    //根据key查询
    @RequestMapping(value = "/findbykey", method = RequestMethod.POST)
    public Result findCsvTempDetailByKey(@RequestBody CsvTemplateDetailDto indto) {
        System.out.println("开始查询...");
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
        System.out.println("开始添加..."+indto.toString());
        csvTemplateDetailService.insertCsvTempDetail(indto);
        return ResultUtil.success(null) ;
    }

    //根据ID删除
    @RequestMapping(value = "/delbyid", method = RequestMethod.GET)
    public Result delCsvTempDetailById(@RequestParam(value = "csvfieldid", required = true) int csvFieldId) {
        System.out.println("开始删除...");
        csvTemplateDetailService.delCsvTempDetailById(csvFieldId); ;
        return ResultUtil.success(null) ;
    }

    //根据ID更新
    @RequestMapping(value = "/updatebyid", method = RequestMethod.POST)
    public Result updCsvTempDetailById(@RequestBody CsvTemplateDetailDto indto) {
        System.out.println("开始更新..."+indto.toString());
        csvTemplateDetailService.updCsvTempDetailById(indto);

        return ResultUtil.success(null) ;
    }

    //批量更新模板字段
    @RequestMapping(value = "/updatedetailbat", method = RequestMethod.POST)
    public Result updCsvTempDetail(@RequestBody CsvTempBatDto indto) {
        System.out.println("开始更新..."+indto.toString());
        csvTemplateDetailService.updCsvTempDetailBat(indto);

        return ResultUtil.success(null) ;
    }
}