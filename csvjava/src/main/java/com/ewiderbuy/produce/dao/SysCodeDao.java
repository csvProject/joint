package com.ewiderbuy.produce.dao;





import com.ewiderbuy.produce.entity.SysCodeDto;
import com.ewiderbuy.produce.entity.SysCodeDto;

import java.util.List;

public interface SysCodeDao {

    //根据分类code查询
    List<SysCodeDto> findSysCodeByTypeCd(int sysTypeCd);

    //根据分类code、代码code查询
    SysCodeDto findSysCodeBySysCd(SysCodeDto into);

    //更新sysnm
    public void updSysNm(SysCodeDto indto);
}
