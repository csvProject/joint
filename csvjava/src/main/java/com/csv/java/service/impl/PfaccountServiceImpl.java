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

    public PfaccountDto findPfaccountByPfacctid(int pfacctid){
        return pfaccountDao.findPfaccountByPfacctid(pfacctid);
    }

    public void delPfaccountByPfacctid(int pfacctid){
        pfaccountDao.delPfaccountByPfacctid(pfacctid);
    }

    public void updPfaccountByPfacctid(PfaccountDto indto){
        pfaccountDao.updPfaccountByPfacctid(indto);
    }

    public void insertPfaccount(PfaccountDto indto){
        pfaccountDao.insertPfaccount(indto);
    }
}
