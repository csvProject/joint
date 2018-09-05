package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.PfaccountDto;
import com.csv.java.service.CsvCustmerFieldService;
import com.csv.java.service.PfaccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/csvcustmerfield")
public class CsvCustmerFieldController {
    @Autowired
    private CsvCustmerFieldService csvCustmerFieldService;

    //根据模板所有自定义公式字段
    @RequestMapping(value = "/findCsvCustmerField", method = RequestMethod.GET)
    public Result findCsvCustmerField(@RequestParam(value = "csvtempid", required = true) int csvtempId) {
        System.out.println("开始查询...");
        return ResultUtil.success(csvCustmerFieldService.findCsvCustmerField(csvtempId)) ;
    }


}