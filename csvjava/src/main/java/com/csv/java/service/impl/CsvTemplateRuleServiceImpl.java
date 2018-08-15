package com.csv.java.service.impl;




import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.entity.CsvTemplateRuleDto;
import com.csv.java.service.CsvTemplateRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "csvTemplateRuleService")
public class CsvTemplateRuleServiceImpl implements CsvTemplateRuleService {

    @Autowired
    private CsvTemplateRuleDao csvTemplateRuleDao;

    public void updCsvTempRuleById(CsvTemplateRuleDto indto){
        csvTemplateRuleDao.updCsvTempRuleById(indto);
    }

}
