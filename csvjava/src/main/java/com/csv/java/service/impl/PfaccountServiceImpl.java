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

    public List<PfaccountDto> findPfaccountByPfid(int pfid){
        return pfaccountDao.findPfaccountByPfid(pfid);
    }

    public PfaccountDto findPfaccountByPfacid(int pfacid){
        return pfaccountDao.findPfaccountByPfacid(pfacid);
    }

    public List<PfaccountDto> findPfaccountByPfacnm(String pfacnm){
        return pfaccountDao.findPfaccountByPfacnm(pfacnm);
    }

    public void delPfaccountByPfacid(int pfacid){
        pfaccountDao.delPfaccountByPfacid(pfacid);
    }

    public void updPfaccountByPfacid(PfaccountDto indto){
        pfaccountDao.updPfaccountByPfacid(indto);
    }

    public void insertPfaccount(PfaccountDto indto){
        pfaccountDao.insertPfaccount(indto);
    }

    public void delPfaccountByPfid(int pfid){
        pfaccountDao.delPfaccountByPfid(pfid);
    }
}
