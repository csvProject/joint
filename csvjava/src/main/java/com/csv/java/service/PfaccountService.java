package com.csv.java.service;



import com.csv.java.entity.PfaccountDto;

import java.util.List;
import java.util.Map;

public interface PfaccountService {
    List<PfaccountDto> findPfaccountByPfacnm(PfaccountDto indto);

    List<PfaccountDto> findPfaccountByPfid(int pfId);

    PfaccountDto findPfaccountByPfacid(int pfacId);

    public int updPfaccountByPfacid( PfaccountDto indto);

    public int insertPfaccount(PfaccountDto indto);

    public void delPfaccountByPfacid(int pfacId);

}
