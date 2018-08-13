package com.csv.java.service.impl;




import com.csv.java.dao.CsvTemplateDetailDao;
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

    @Autowired
    private CsvTemplateDetailDao csvTemplateDetailDao;

    public CsvTemplateInfoDto findCsvTempInfoById(int csvtempId){
        return csvTemplateInfoDao.findCsvTempInfoById(csvtempId);
    }

    public List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto){
        return csvTemplateInfoDao.findCsvTempInfoByCondi(indto);
    }

    public void delCsvTempInfoById(int csvtempId){
        csvTemplateInfoDao.delCsvTempInfoById(csvtempId);
        csvTemplateDetailDao.delCsvTempDetailBycsvtempId(csvtempId);
    }

    public void updCsvTempInfoById(CsvTemplateInfoDto indto){
        csvTemplateInfoDao.updCsvTempInfoById(indto);
    }

    public void insertCsvTempInfo(CsvTemplateInfoDto indto){
        csvTemplateInfoDao.insertCsvTempInfo(indto);
    }
}
