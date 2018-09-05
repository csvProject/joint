package com.csv.java.service.impl;

import com.csv.java.dao.CsvCustmerFieldDao;
import com.csv.java.entity.CsvCustmerFieldDto;
import com.csv.java.service.CsvCustmerFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "csvCustmerFieldService")
public class CsvCustmerFieldServiceImpl implements CsvCustmerFieldService {

    @Autowired
    private CsvCustmerFieldDao csvCustmerFieldDao;

    public List<CsvCustmerFieldDto> findCsvCustmerField(int csvtempId){
        return csvCustmerFieldDao.findCsvCustmerField(csvtempId);
    }



}
