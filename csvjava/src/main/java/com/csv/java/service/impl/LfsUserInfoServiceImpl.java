package com.csv.java.service.impl;

import com.csv.java.dao.LfsUserInfoDao;
import com.csv.java.entity.LfsUserInfoDto;
import com.csv.java.service.LfsUserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "lfsUserInfoService")
public class LfsUserInfoServiceImpl implements LfsUserInfoService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private LfsUserInfoDao lfsUserInfoDao;

    public List<LfsUserInfoDto> loginForGetUserInfo(LfsUserInfoDto indto){
        return lfsUserInfoDao.loginForGetUserInfo(indto);
    }

}
