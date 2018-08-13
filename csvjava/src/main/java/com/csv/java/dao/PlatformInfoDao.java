package com.csv.java.dao;




import com.csv.java.entity.PlatformInfoDto;

import java.util.List;

public interface PlatformInfoDao {
    //根据平台ID查询
    PlatformInfoDto findPlatformInfoByPfid(int pfid);

    //平台信息更新
    public void updPlatformInfoByPfid(PlatformInfoDto indto);

    //平台添加
    public void insertPlatformInfo(PlatformInfoDto indto);

    //删除平台信息
    public void delPlatformInfoByPfid(int pfid);

    //根据平台名称查询
    List<PlatformInfoDto> findPlatformInfoByPfnm(String pfnm);

}
