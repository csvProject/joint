package com.ewiderbuy.produce.controller;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.ewiderbuy.produce.common.tool.Md5Util;
import com.ewiderbuy.produce.common.tool.ServiceUtil;
import com.ewiderbuy.produce.entity.LfsUserInfoDto;
import com.ewiderbuy.produce.service.LfsUserInfoService;
import com.ewiderbuy.produce.common.tool.Md5Util;
import com.ewiderbuy.produce.service.LfsUserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by wth on 2018/1/27
 * 用户信息
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/ifcUser")
public class LfsUserInfoController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    LfsUserInfoService lfsUserInfoService;

    /**
     * 用户登录
     * @param inData
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result getLogin(@RequestBody LfsUserInfoDto inData) {
        LfsUserInfoDto info = new LfsUserInfoDto();
        try {

            inData.setPassword(Md5Util.MD5(inData.getPassword()));
            List<LfsUserInfoDto> resList = lfsUserInfoService.loginForGetUserInfo(inData);
            if (resList != null && resList.size() > 0) {
                //生成token
                info.setId(resList.get(0).getId());
                info.setEmail(resList.get(0).getEmail());
                info.setGid(resList.get(0).getGid());
                info.setRealname(resList.get(0).getRealname());
                info.setTel(resList.get(0).getTel());
                info.setUsername(resList.get(0).getUsername());
                info.setToken(ServiceUtil.getToken(String.valueOf(info.getId())));
                return ResultUtil.success(0,"登录成功",info);
            } else {
                return ResultUtil.success(-1, "用户名或密码输入错误,请重新输入", null);
            }


        } catch (Exception e) {
            return ResultUtil.success(-1, "登录获取用户信息异常，异常信息", null);
        }
    }
}
