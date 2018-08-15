package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.PfaccountDto;
import com.csv.java.service.PfaccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pfaccount")
public class PfaccountController {
    @Autowired
    private PfaccountService pfaccountService;

    //根据账号ID查询账号信息
    @RequestMapping(value = "/findbypfacid", method = RequestMethod.GET)
    public Result findPfaccountByPfacid(@RequestParam(value = "pfacid", required = true) int pfacId) {
        System.out.println("开始查询...");
        return ResultUtil.success(pfaccountService.findPfaccountByPfacid(pfacId)) ;
    }

    //根据平台ID查询平台下所有账号
    @RequestMapping(value = "/findbypfid", method = RequestMethod.GET)
    public Result findPfaccountByPfid(@RequestParam(value = "pfid", required = true) int pfId) {
        System.out.println("开始查询...");
        return ResultUtil.success(pfaccountService.findPfaccountByPfid(pfId)) ;
    }

    //根据账号名称查账号
    @RequestMapping(value = "/findbypfacnm", method = RequestMethod.POST)
    public Result findPfaccountByPfacnm(@RequestBody PfaccountDto indto) {
        System.out.println("开始查询...");
        return ResultUtil.success(pfaccountService.findPfaccountByPfacnm(indto)) ;
    }

    /**
     * 插入账号
     * REST Client post Content-Type:application/json;charset=UTF-8
     * Text : {"pfaccountNm":"yahoo账号3","platformId":1,"memo":"","logId":1}
     * @author wkm
     * @since 2018/8/9
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertPfaccount(@RequestBody PfaccountDto pfaccountDto) {
        System.out.println("开始添加..."+pfaccountDto.toString());
        pfaccountService.insertPfaccount(pfaccountDto);
        return ResultUtil.success(null) ;
    }

    //根据账号ID删除账号
    @RequestMapping(value = "/delbypfacid", method = RequestMethod.GET)
    public Result delPfaccountByPfacid(@RequestParam(value = "pfacid", required = true) int pfacId) {
        System.out.println("开始删除...");
        pfaccountService.delPfaccountByPfacid(pfacId) ;
        return ResultUtil.success(null) ;
    }

    //根据账号ID更新
    @RequestMapping(value = "/updatebypfacid", method = RequestMethod.POST)
    public Result updPfaccountByPfacid(@RequestBody PfaccountDto pfaccountDto) {
        System.out.println("开始更新..."+pfaccountDto.toString());
        pfaccountService.updPfaccountByPfacid(pfaccountDto);
        return ResultUtil.success(null) ;
    }


}