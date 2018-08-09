package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.Ginfo;
import com.csv.java.service.GinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

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

     /**
      * findUsersPost
      * REST Client post Content-Type:application/json;charset=UTF-8  Text : {"id":1,"groupName":"123"}
      * @author wkm
      * @since 2018/8/9
      */
    @RequestMapping(value = "/ginfoAll", method = RequestMethod.POST)
    public Result findUsersPost(@RequestBody Ginfo ginfo) {
        System.out.println("开始查询..."+ginfo.toString());
        return ResultUtil.success(ginfoService.findGinfo());
    }

}