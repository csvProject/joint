package com.csv.java.dao;


import com.csv.java.entity.PfaccountDto;

import java.util.List;
import java.util.Map;

public interface PfaccountDao {
    List<PfaccountDto> findPfaccountByPfid(int pfid);

    PfaccountDto findPfaccountByPfacid(int pfacid);

    public void updPfaccountByPfacid( PfaccountDto indto);

    public void insertPfaccount(PfaccountDto indto);

    public void delPfaccountByPfacid(int pfacid);

    List<PfaccountDto> findPfaccountByPfacnm(String Pfacnm);

    public void delPfaccountByPfid(int pfid);
}
