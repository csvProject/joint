package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.service.PfaccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pfaccount")
public class PfaccountController {
    @Autowired
    private PfaccountService pfaccountService;

  /*  @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public Result findByUserId(@RequestParam(value = "userId", required = true) int userId) {
        System.out.println("开始查询...");
        return ResultUtil.success(ginfoService.findUserById(userId));
    }*/

    //根据平台ID查询平台下所有账号
    @RequestMapping(value = "/PfaccountByPfid", method = RequestMethod.GET)
    public Result findPfaccountByPfid(@RequestParam(value = "pfid", required = true) int pfid) {
        System.out.println("开始查询...");
        return ResultUtil.success(pfaccountService.findPfaccountByPfid(pfid)) ;
    }


}