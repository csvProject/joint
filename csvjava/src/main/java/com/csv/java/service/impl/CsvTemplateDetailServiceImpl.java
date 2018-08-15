package com.csv.java.service.impl;




import com.csv.java.dao.CsvTemplateDetailDao;
import com.csv.java.dao.CsvTemplateRuleDao;
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

        //更新模板规则表中的csvsql处理----
        String csvSql="";

        CsvTemplateRuleDto csvTemplateRuleDto =new CsvTemplateRuleDto();
        csvTemplateRuleDto.setCsvSql(csvSql);
        csvTemplateRuleDto.setCsvtempId(indto.getCsvtempId());
        csvTemplateRuleDao.updCsvTempRuleById(csvTemplateRuleDto);
        //--------------------------
    }

    public void insertCsvTempDetail(CsvTemplateDetailDto indto){
        csvTemplateDetailDao.insertCsvTempDetail(indto);
    }
}
