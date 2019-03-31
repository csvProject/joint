package com.csv.java.service;
import com.csv.java.entity.LfsUserInfoDto;

import java.util.List;

/**
 * Create by wth on 2018/1/27
 */
public interface LfsUserInfoService {


    /**
     * 登录获取用户信息
     * @throws Exception
     */
    public List<LfsUserInfoDto> loginForGetUserInfo(LfsUserInfoDto indto) throws Exception;


}
