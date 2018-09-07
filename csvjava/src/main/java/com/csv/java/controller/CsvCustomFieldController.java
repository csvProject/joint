package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.CsvCustomFieldBatDto;
import com.csv.java.service.CsvCustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/csvcustomfield")
public class CsvCustomFieldController {
    @Autowired
    private CsvCustomFieldService csvCustomFieldService;

    //根据模板所有自定义公式字段
    @RequestMapping(value = "/findCsvCustomField", method = RequestMethod.GET)
    public Result findCsvCustomField(@RequestParam(value = "csvtempid", required = true) int csvtempId) {
        System.out.println("开始查询...");
        return ResultUtil.success(csvCustomFieldService.findCsvCustomField(csvtempId)) ;
    }

    //批量更新模板自定义字段
    @RequestMapping(value = "/updcsvctmfieldbat", method = RequestMethod.POST)
    public Result updCsvCustomFieldBat(@RequestBody CsvCustomFieldBatDto indto) {
        System.out.println("开始更新..."+indto.toString());
        csvCustomFieldService.updCsvCustomFieldBat(indto);
        return ResultUtil.success(null) ;
    }

    //验证公式是否正确
    @RequestMapping(value = "/verifyformula", method = RequestMethod.GET)
    public Result verifyFormula(@RequestParam(value = "formula", required = true) String formula) {
        System.out.println("开始查询...");
        return ResultUtil.success(csvCustomFieldService.verifyFormula(formula)) ;
    }
}