package com.csv.java.dao;




import com.csv.java.entity.PlatformInfoDto;

import java.util.List;

public interface PlatformInfoDao {
    //根据平台ID查询
    PlatformInfoDto findPlatformInfoByPfid(int pfId);

    //平台信息更新
    public void updPlatformInfoByPfid(PlatformInfoDto indto);

    //平台添加
    public void insertPlatformInfo(PlatformInfoDto indto);

    //删除平台信息
    public void delPlatformInfoByPfid(int pfId);

    //根据平台名称查询
    List<PlatformInfoDto> findPlatformInfoByPfnm(String pfNm);

    //判断平台是否存在
    public int checkPlatformOnly(String pfNm);

}
