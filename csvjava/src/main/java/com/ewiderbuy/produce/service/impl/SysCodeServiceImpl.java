package com.ewiderbuy.produce.service.impl;





import com.ewiderbuy.produce.dao.SysCodeDao;
import com.ewiderbuy.produce.entity.SysCodeDto;
import com.ewiderbuy.produce.service.SysCodeService;
import com.ewiderbuy.produce.dao.SysCodeDao;
import com.ewiderbuy.produce.entity.SysCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "sysCodeService")
public class SysCodeServiceImpl implements SysCodeService {

    @Autowired
    private SysCodeDao sysCodeDao;


    public List<SysCodeDto> findSysCodeByTypeCd(int sysTypeCd){
        return sysCodeDao.findSysCodeByTypeCd(sysTypeCd);
    }

    public SysCodeDto findSysCodeBySysCd(SysCodeDto indto){
        return sysCodeDao.findSysCodeBySysCd(indto);
    }

}
