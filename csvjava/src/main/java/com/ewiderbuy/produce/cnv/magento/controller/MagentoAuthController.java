package com.ewiderbuy.produce.cnv.magento.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.cnv.magento.serivce.MagentoAuthService;
import com.ewiderbuy.produce.cnv.magento.model.ApiTokenInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/magentoauth")
public class MagentoAuthController {
    @Autowired
    private MagentoAuthService magentoAuthService;

    //获取请求token及验证url
    @RequestMapping(value = "/getrequesttoken", method = RequestMethod.POST)
    public Result getRequestToken(@RequestBody ApiTokenInDto indto) {
        System.out.println("开始获取...");
        return ResultUtil.success(magentoAuthService.getRequestToken(indto)) ;
    }

    //获取访问数据token
    @RequestMapping(value = "/getaccesstoken", method = RequestMethod.POST)
    public Result getAccessToken(@RequestBody ApiTokenInDto indto) {
        System.out.println("开始获取...");
        return ResultUtil.success(magentoAuthService.getAcessToken(indto)) ;
    }

}