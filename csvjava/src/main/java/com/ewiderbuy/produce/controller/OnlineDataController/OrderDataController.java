package com.ewiderbuy.produce.controller.OnlineDataController;

import com.ewiderbuy.produce.OnlineDataService.OrderDataService;
import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
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

        orderDataService.generateOrderDataFromMagento(limitcount);
        return ResultUtil.success() ;
    }

}