package com.csv.java.dao;


import com.csv.java.entity.PfaccountDto;

import java.util.List;
import java.util.Map;

public interface PfaccountDao {

    //根据平台ID查询所有账号
    List<PfaccountDto> findPfaccountByPfid(int pfId);

    //根据账号ID查询账号信息
    PfaccountDto findPfaccountByPfacid(int pfacId);

    //根据账号ID更新
    public void updPfaccountByPfacid( PfaccountDto indto);

    //添加账号
    public void insertPfaccount(PfaccountDto indto);

    //根据账号ID删除
    public void delPfaccountByPfacid(int pfacId);

    //根据账号名称查询
    List<PfaccountDto> findPfaccountByPfacnm(PfaccountDto into);

    //根据平台ID删除所有账号
    public void delPfaccountByPfid(int pfId);
}
