package com.ewiderbuy.produce.service.impl;

import com.ewiderbuy.produce.dao.PfaccountDao;
import com.ewiderbuy.produce.entity.PfaccountDto;
import com.ewiderbuy.produce.service.PfaccountService;
import com.ewiderbuy.produce.dao.PfaccountDao;
import com.ewiderbuy.produce.entity.PfaccountDto;
import com.ewiderbuy.produce.service.PfaccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "pfaccountService")
public class PfaccountServiceImpl implements PfaccountService {

    @Autowired
    private PfaccountDao pfaccountDao;

    public List<PfaccountDto> findPfaccountByPfid(int pfId){
        return pfaccountDao.findPfaccountByPfid(pfId);
    }

    public PfaccountDto findPfaccountByPfacid(int pfacId){
        return pfaccountDao.findPfaccountByPfacid(pfacId);
    }

    public List<PfaccountDto> findPfaccountByPfacnm(PfaccountDto indto){
        return pfaccountDao.findPfaccountByPfacnm(indto);
    }

    public void delPfaccountByPfacid(int pfacId){
        pfaccountDao.delPfaccountByPfacid(pfacId);
    }

    public int updPfaccountByPfacid(PfaccountDto indto){
        int ret =0;
        ret = pfaccountDao.checkPfaccountOnly(indto);

        if (ret >0){
            return -1;
        }
        pfaccountDao.updPfaccountByPfacid(indto);
        return ret;

    }

    public int insertPfaccount(PfaccountDto indto){
        int ret =0;
        ret = pfaccountDao.chkAddPfaccountOnly(indto);

        if (ret >0){
            return -1;
        }
        pfaccountDao.insertPfaccount(indto);

        return ret;

    }

    private int check(PfaccountDto indto){
        int ret = 0;
        //平台账号名称不能重复
        int temponly  = pfaccountDao.checkPfaccountOnly(indto);

        if (temponly > 0) {
            ret = -1;
        }
        return ret;
    }
}
