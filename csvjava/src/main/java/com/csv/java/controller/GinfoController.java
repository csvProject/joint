package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.service.GinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ginfo")
public class GinfoController {
    @Autowired
    private GinfoService ginfoService;

  /*  @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public Result findByUserId(@RequestParam(value = "userId", required = true) int userId) {
        System.out.println("开始查询...");
        return ResultUtil.success(ginfoService.findUserById(userId));
    }*/

    @RequestMapping(value = "/ginfoAll", method = RequestMethod.GET)
    public Result findUsers() {
        System.out.println("开始查询...");
        return ResultUtil.success(ginfoService.findGinfo()) ;
    }


}