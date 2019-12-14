package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.entity.CsvCustomFieldBatDto;
import com.ewiderbuy.produce.entity.CsvCustomFieldDto;
import com.ewiderbuy.produce.entity.CsvTemplateDetailDto;
import com.ewiderbuy.produce.service.CsvCustomFieldService;
import com.ewiderbuy.produce.entity.CsvCustomFieldDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/csvcustomfield")
public class CsvCustomFieldController {
    @Autowired
    private CsvCustomFieldService csvCustomFieldService;

    //根据模板所有自定义公式字段
    @RequestMapping(value = "/findCsvCustomField", method = RequestMethod.GET)
    public Result findCsvCustomField(@RequestParam(value = "csvtempid", required = true) int csvtempId) {

        return ResultUtil.success(csvCustomFieldService.findCsvCustomField(csvtempId)) ;
    }

    //批量更新模板自定义字段
    @RequestMapping(value = "/updcsvctmfieldbat", method = RequestMethod.POST)
    public Result updCsvCustomFieldBat(@RequestBody CsvCustomFieldBatDto indto) {

        csvCustomFieldService.updCsvCustomFieldBat(indto);
        return ResultUtil.success(null) ;
    }

    //验证公式是否正确
    @RequestMapping(value = "/verifyformula", method = RequestMethod.GET)
    public Result verifyFormula(@RequestParam(value = "formula", required = true) String formula) {

        return ResultUtil.success(csvCustomFieldService.verifyFormula(formula)) ;
    }

    //自定义公式是否可以删除
    @RequestMapping(value = "/chkdelcustom", method = RequestMethod.POST)
    public Result chkDelCustomField(@RequestBody CsvCustomFieldDto indto) {

        List<CsvTemplateDetailDto> chk = new ArrayList<>();
        chk = csvCustomFieldService.chkDelCustomField(indto);
        return ResultUtil.success(chk) ;

    }
}