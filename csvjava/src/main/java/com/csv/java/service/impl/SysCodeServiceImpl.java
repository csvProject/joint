package com.csv.java.service.impl;





import com.csv.java.dao.SysCodeDao;
import com.csv.java.entity.SysCodeDto;
import com.csv.java.service.SysCodeService;
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
