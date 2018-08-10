package com.csv.java.service.impl;


import com.csv.java.dao.PfaccountDao;
import com.csv.java.dao.PlatformInfoDao;
import com.csv.java.entity.PlatformInfoDto;
import com.csv.java.service.PlatformInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "platformInfoService")
public class PlatformInfoServiceImpl implements PlatformInforService {

    @Autowired
    private PlatformInfoDao platformInfoDao;

    @Autowired
    private PfaccountDao pfaccountDao;

    public PlatformInfoDto findPlatformInfoByPfid(int pfid){
        return platformInfoDao.findPlatformInfoByPfid(pfid);
    }

    public List<PlatformInfoDto> findPlatformInfoByPfnm(String pfnm){
        return platformInfoDao.findPlatformInfoByPfnm(pfnm);
    }

    public void delPlatformInfoByPfid(int pfid){
        platformInfoDao.delPlatformInfoByPfid(pfid);
        pfaccountDao.delPfaccountByPfid(pfid);
    }

    public void updPlatformInfoByPfid(PlatformInfoDto indto){
        platformInfoDao.updPlatformInfoByPfid(indto);
    }

    public void insertPlatformInfo(PlatformInfoDto indto){
        platformInfoDao.insertPlatformInfo(indto);
    }
}
