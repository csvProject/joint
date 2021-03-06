package com.ewiderbuy.produce.dao;





import com.ewiderbuy.produce.entity.CsvTemplateRuleDto;
import com.ewiderbuy.produce.entity.CsvTemplateRuleDto;

import java.util.List;

public interface CsvTemplateRuleDao {
    //根据模板规则ID查询
    CsvTemplateRuleDto findCsvTempRuleById(int csvtempRuleId);

    //根据模板ID查询
    CsvTemplateRuleDto findCsvTempRuleByPfid(int csvtempId);

    //模板信息规则更新
    public void updCsvTempRuleById(CsvTemplateRuleDto indto);

    //添加
    public void insertCsvTempRule(CsvTemplateRuleDto indto);

    //删除
    public void delCsvTempRuleById(int csvtempRuleId);

    //删除模板下所有规则
    public void delCsvTempRuleBycsvtempId(int csvtempId);

    //根据4要素查询所有csvsql
    CsvTemplateRuleDto findCsvsqlByFourKey(String fourKey);
}
