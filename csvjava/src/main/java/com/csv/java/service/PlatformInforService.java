package com.csv.java.service;




import com.csv.java.entity.PlatformInfoDto;

import java.util.List;

public interface PlatformInforService {
    List<PlatformInfoDto> findPlatformInfoByPfnm(String pfNm);

    PlatformInfoDto findPlatformInfoByPfid(int pfId);

    public void updPlatformInfoByPfid(PlatformInfoDto indto);

    public void insertPlatformInfo(PlatformInfoDto indto);

    public void delPlatformInfoByPfid(int pfId);
}
