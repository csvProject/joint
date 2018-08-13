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

    public List<PfaccountDto> findPfaccountByPfacnm(String pfacNm){
        return pfaccountDao.findPfaccountByPfacnm(pfacNm);
    }

    public void delPfaccountByPfacid(int pfacId){
        pfaccountDao.delPfaccountByPfacid(pfacId);
    }

    public void updPfaccountByPfacid(PfaccountDto indto){
        pfaccountDao.updPfaccountByPfacid(indto);
    }

    public void insertPfaccount(PfaccountDto indto){
        pfaccountDao.insertPfaccount(indto);
    }

}
