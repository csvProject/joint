package com.csv.java.service.impl;




import com.csv.java.dao.CsvTemplateInfoDao;
import com.csv.java.entity.CsvTemplateInfoDto;
import com.csv.java.service.CsvTemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "csvTemplateInfoService")
public class CsvTemplateInfoServiceImpl implements CsvTemplateInfoService {

    @Autowired
    private CsvTemplateInfoDao csvTemplateInfoDao;

    public CsvTemplateInfoDto findCsvTempInfoById(int csvtempid){
        return csvTemplateInfoDao.findCsvTempInfoById(csvtempid);
    }

    public List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto){
        return csvTemplateInfoDao.findCsvTempInfoByCondi(indto);
    }

    public void delCsvTempInfoById(int csvtempid){
        csvTemplateInfoDao.delCsvTempInfoById(csvtempid);
        //.......????
    }

    public void updCsvTempInfoById(CsvTemplateInfoDto indto){
        csvTemplateInfoDao.updCsvTempInfoById(indto);
    }

    public void insertCsvTempInfo(CsvTemplateInfoDto indto){
        csvTemplateInfoDao.insertCsvTempInfo(indto);
    }
}
