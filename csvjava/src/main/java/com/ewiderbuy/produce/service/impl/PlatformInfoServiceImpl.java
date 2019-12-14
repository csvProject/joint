package com.ewiderbuy.produce.service.impl;


import com.ewiderbuy.produce.dao.PfaccountDao;
import com.ewiderbuy.produce.dao.PlatformInfoDao;
import com.ewiderbuy.produce.entity.PlatformInfoDto;
import com.ewiderbuy.produce.service.PlatformInforService;
import com.ewiderbuy.produce.dao.PfaccountDao;
import com.ewiderbuy.produce.dao.PlatformInfoDao;
import com.ewiderbuy.produce.entity.PlatformInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void delPlatformInfoByPfid(int pfId){
        platformInfoDao.delPlatformInfoByPfid(pfId);
        pfaccountDao.delPfaccountByPfid(pfId);
    }

    public int updPlatformInfoByPfid(PlatformInfoDto indto){
        int ret  = 0;

        ret = platformInfoDao.checkPlatformOnly(indto);

        if (ret > 0){
            return -1;
        }
        platformInfoDao.updPlatformInfoByPfid(indto);
        return ret;

    }

    public int insertPlatformInfo(PlatformInfoDto indto){
        int ret  = 0;

        ret = platformInfoDao.chkAddPlatformOnly(indto.getPlatformNm());

        if (ret > 0){
            return -1;
        }
        platformInfoDao.insertPlatformInfo(indto);

        return ret;
    }

}
