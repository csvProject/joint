package com.csv.java.service;






import com.csv.java.entity.SysCodeDto;

import java.util.List;

public interface SysCodeService {
    List<SysCodeDto> findSysCodeByTypeCd(int sysTypeCd);

    SysCodeDto findSysCodeBySysCd(SysCodeDto indto);
}
