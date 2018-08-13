package com.csv.java.controller;

import com.csv.java.common.result.Result;
import com.csv.java.common.result.ResultUtil;
import com.csv.java.entity.PlatformInfoDto;
import com.csv.java.service.PlatformInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/platform")
public class PlatformInfoController {
    @Autowired
    private PlatformInforService platformInforService;

    //根据平台ID查询
    @RequestMapping(value = "/findbypfid", method = RequestMethod.GET)
    public Result findPlatformInfoByPfid(@RequestParam(value = "pfid", required = true) int pfId) {
        System.out.println("开始查询...");
        return ResultUtil.success(platformInforService.findPlatformInfoByPfid(pfId));
    }

    //根据平台名称查询
    @RequestMapping(value = "/findbypfnm", method = RequestMethod.GET)
    public Result findPlatformInfoByPfnm(@RequestParam(value = "pfnm", required = true) String pfNm) {
        System.out.println("开始查询...");
        return ResultUtil.success(platformInforService.findPlatformInfoByPfnm(pfNm)) ;
    }

    /**
     * 添加平台
     * REST Client post Content-Type:application/json;charset=UTF-8  Text :
     * {"platformNm":"yahoo平台（日文）","platformType":2,"memo":"112","logId":1}
     * @author wkm
     * @since 2018/8/9
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertPlatformInfo(@RequestBody PlatformInfoDto platformInfoDto) {
        System.out.println("开始添加..."+platformInfoDto.toString());
        platformInforService.insertPlatformInfo(platformInfoDto);
        return ResultUtil.success(null) ;
    }

    //根据平台ID删除平台及账号
    @RequestMapping(value = "/delbypfid", method = RequestMethod.GET)
    public Result delPlatformInfoByPfid(@RequestParam(value = "pfid", required = true) int pfId) {
        System.out.println("开始删除...");
        platformInforService.delPlatformInfoByPfid(pfId); ;
        return ResultUtil.success(null) ;
    }

    //根据平台ID更新
    @RequestMapping(value = "/updatebypfid", method = RequestMethod.POST)
    public Result updPlatformInfoByPfid(@RequestBody PlatformInfoDto platformInfoDto) {
        System.out.println("开始更新..."+platformInfoDto.toString());
        platformInforService.updPlatformInfoByPfid(platformInfoDto);
        return ResultUtil.success(null) ;
    }

}