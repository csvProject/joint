package com.csv.java.controller.OnlineDataController;

import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/onlinedata")
public class OrderDataController {
    @Autowired
    private OrderDataService orderDataService;

    //手动执行同步处理
    @RequestMapping(value = "/orderfrommagento", method = RequestMethod.GET)
    public Result orderFromMagento(@RequestParam(value = "limitcount", required = true) int limitcount) {
        System.out.println("开始同步...");
        orderDataService.generateOrderDataFromMagento(limitcount);
        return ResultUtil.success() ;
    }

}