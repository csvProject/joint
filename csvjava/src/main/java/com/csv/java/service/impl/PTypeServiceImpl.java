package com.csv.java.service.impl;





import com.csv.java.dao.PTypeDao;
import com.csv.java.entity.PTypeDto;
import com.csv.java.service.PTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ptypeService")
public class PTypeServiceImpl implements PTypeService {

    @Autowired
    private PTypeDao ptypeDao;


    public List<PTypeDto> findPTypeByCondi(PTypeDto indto){
        return ptypeDao.findPTypeByCondi(indto);
    }

    public List<PTypeDto> findPTypeByCodenm(String cdnm){
        return ptypeDao.findPTypeByCodenm(cdnm);
    }

}
