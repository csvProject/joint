package com.csv.java.service.impl;




import com.csv.java.dao.CsvTemplateDetailDao;
import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.entity.CsvTempBatDto;
import com.csv.java.entity.CsvTemplateDetailDto;
import com.csv.java.entity.CsvTemplateRuleDto;
import com.csv.java.service.CsvTemplateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "csvTemplateDetailService")
public class CsvTemplateDetailServiceImpl implements CsvTemplateDetailService {

    @Autowired
    private CsvTemplateDetailDao csvTemplateDetailDao;

    @Autowired
    private CsvTemplateRuleDao csvTemplateRuleDao;

    public CsvTemplateDetailDto findCsvTempDetailById(int csvFieldId){
        return csvTemplateDetailDao.findCsvTempDetailById(csvFieldId);
    }

    public List<CsvTemplateDetailDto> findCsvTempDetailByKey(CsvTemplateDetailDto indto){
        return csvTemplateDetailDao.findCsvTempDetailByKey(indto);
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

    public void updCsvTempDetailBat(CsvTempBatDto indto){
        //删除模板下所有字段
        csvTemplateDetailDao.delCsvTempDetailBycsvtempId(indto.getCsvtempId());

        //批量添加模板字段
        //处理代码代码---
        for (int i=0 ;i<1;i++) {
            CsvTemplateDetailDto dtlDto = new CsvTemplateDetailDto();
            csvTemplateDetailDao.insertCsvTempDetail(dtlDto);
        }
        //----

        //更新模板规则表中的csvsql处理----
        String csvSql="";

        //处理代码代码---

        //----
        CsvTemplateRuleDto csvTemplateRuleDto =new CsvTemplateRuleDto();
        csvTemplateRuleDto.setCsvSql(csvSql);
        csvTemplateRuleDto.setCsvtempId(indto.getCsvtempId());
        csvTemplateRuleDao.updCsvTempRuleById(csvTemplateRuleDto);
        //--------------------------

    }
}
