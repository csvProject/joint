package com.csv.java.service.impl;

import com.csv.java.dao.GinfoDao;
import com.csv.java.entity.Ginfo;
import com.csv.java.service.GinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ginfoService")
public class GinfoServiceImpl implements GinfoService {

    @Autowired
    private GinfoDao ginfoDao;

    public List<Ginfo> findGinfo(){
        return ginfoDao.findGinfo();
    }


}
