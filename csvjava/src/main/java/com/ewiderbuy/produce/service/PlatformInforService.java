package com.ewiderbuy.produce.service;




import com.ewiderbuy.produce.entity.PlatformInfoDto;
import com.ewiderbuy.produce.entity.PlatformInfoDto;

import java.util.List;

public interface PlatformInforService {
    List<PlatformInfoDto> findPlatformInfoByPfnm(String pfNm);

    PlatformInfoDto findPlatformInfoByPfid(int pfId);

    public int updPlatformInfoByPfid(PlatformInfoDto indto);

    public int insertPlatformInfo(PlatformInfoDto indto);

    public void delPlatformInfoByPfid(int pfId);
}
