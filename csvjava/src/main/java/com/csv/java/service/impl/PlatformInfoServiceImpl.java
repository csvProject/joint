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

    public PlatformInfoDto findPlatformInfoByPfid(int pfId){
        return platformInfoDao.findPlatformInfoByPfid(pfId);
    }

    public List<PlatformInfoDto> findPlatformInfoByPfnm(String pfNm){
        return platformInfoDao.findPlatformInfoByPfnm(pfNm);
    }

    public void delPlatformInfoByPfid(int pfId){
        platformInfoDao.delPlatformInfoByPfid(pfId);
        pfaccountDao.delPfaccountByPfid(pfId);
    }

    public int updPlatformInfoByPfid(PlatformInfoDto indto){
        int ret =0;
        ret = check(indto);

        if (ret != -1){
            platformInfoDao.updPlatformInfoByPfid(indto);
        }
        return ret;

    }

    public int insertPlatformInfo(PlatformInfoDto indto){
        int ret =0;
        ret = check(indto);

        if (ret != -1){
            platformInfoDao.insertPlatformInfo(indto);
        }
        return ret;
    }

    private int check(PlatformInfoDto indto){
        int ret = 0;
        //平台名称不能重复
        int temponly  = platformInfoDao.checkPlatformOnly(indto.getPlatformNm());

        if (temponly > 0) {
            ret = -1;
        }
        return ret;
    }
}
