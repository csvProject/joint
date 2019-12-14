package com.ewiderbuy.produce.service;






import com.ewiderbuy.produce.entity.SysCodeDto;
import com.ewiderbuy.produce.entity.SysCodeDto;

import java.util.List;

public interface SysCodeService {
    List<SysCodeDto> findSysCodeByTypeCd(int sysTypeCd);

    SysCodeDto findSysCodeBySysCd(SysCodeDto indto);
}
