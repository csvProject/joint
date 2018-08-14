package com.csv.java.service.impl;




import com.csv.java.dao.CsvTemplateDetailDao;
import com.csv.java.entity.CsvTemplateDetailDto;
import com.csv.java.service.CsvTemplateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "csvTemplateDetailService")
public class CsvTemplateDetailServiceImpl implements CsvTemplateDetailService {

    @Autowired
    private CsvTemplateDetailDao csvTemplateDetailDao;

    public CsvTemplateDetailDto findCsvTempDetailById(int csvFieldId){
        return csvTemplateDetailDao.findCsvTempDetailById(csvFieldId);
    }

    public List<CsvTemplateDetailDto> findCsvTempDetailByKey(String fieldKey){
        return csvTemplateDetailDao.findCsvTempDetailByKey(fieldKey);
    }

    public void delCsvTempDetailById(int csvFieldId){
        csvTemplateDetailDao.delCsvTempDetailById(csvFieldId);
    }

    public void updCsvTempDetailById(CsvTemplateDetailDto indto){
        csvTemplateDetailDao.updCsvTempDetailById(indto);
    }

    public void insertCsvTempDetail(CsvTemplateDetailDto indto){
        csvTemplateDetailDao.insertCsvTempDetail(indto);
    }
}
