package com.csv.java.service.impl;

import com.csv.java.dao.PfaccountDao;
import com.csv.java.entity.PfaccountDto;
import com.csv.java.service.PfaccountService;
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
        ret = check(indto);

        if (ret != -1){
            pfaccountDao.updPfaccountByPfacid(indto);
        }
        return ret;

    }

    public int insertPfaccount(PfaccountDto indto){
        int ret =0;
        ret = check(indto);

        if (ret != -1){
            pfaccountDao.insertPfaccount(indto);
        }
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
