package com.ewiderbuy.produce.service.impl;




import com.ewiderbuy.produce.dao.CsvTemplateRuleDao;
import com.ewiderbuy.produce.entity.CsvTemplateRuleDto;
import com.ewiderbuy.produce.service.CsvTemplateRuleService;
import com.ewiderbuy.produce.dao.CsvTemplateRuleDao;
import com.ewiderbuy.produce.entity.CsvTemplateRuleDto;
import com.ewiderbuy.produce.service.CsvTemplateRuleService;
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
