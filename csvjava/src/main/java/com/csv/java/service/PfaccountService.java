package com.csv.java.service;



import com.csv.java.entity.PfaccountDto;

import java.util.List;
import java.util.Map;

public interface PfaccountService {
    List<PfaccountDto> findPfaccountByPfacnm(String pfacnm);

    List<PfaccountDto> findPfaccountByPfid(int pfid);

    PfaccountDto findPfaccountByPfacid(int pfacid);

    public void updPfaccountByPfacid( PfaccountDto indto);

    public void insertPfaccount(PfaccountDto indto);

    public void delPfaccountByPfacid(int pfacid);

    public void delPfaccountByPfid(int pfid);
}
