package com.csv.java.service;




import com.csv.java.entity.PlatformInfoDto;

import java.util.List;

public interface PlatformInforService {
    List<PlatformInfoDto> findPlatformInfoByPfnm(String pfnm);

    PlatformInfoDto findPlatformInfoByPfid(int pfid);

    public void updPlatformInfoByPfid(PlatformInfoDto indto);

    public void insertPlatformInfo(PlatformInfoDto indto);

    public void delPlatformInfoByPfid(int pfid);
}
