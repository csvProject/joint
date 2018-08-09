package com.csv.java.dao;


import com.csv.java.entity.PfaccountDto;

import java.util.List;
import java.util.Map;

public interface PfaccountDao {
    List<PfaccountDto> findPfaccountByPfid(int pfid);

    PfaccountDto findPfaccountByPfacctid(int pfacctid);

    public void updPfaccountByPfacctid( PfaccountDto indto);

    public void insertPfaccount(PfaccountDto indto);

    public void delPfaccountByPfacctid(int pfacctid);
}
