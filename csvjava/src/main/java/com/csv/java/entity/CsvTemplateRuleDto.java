package com.csv.java.entity;

import java.util.Date;

//模板规则dto
public class CsvTemplateRuleDto extends BaseDto{
    //CSV模板ID
    private int csvtempId;

    //模板规则ID
    private int csvtempRuleId;

    //CSVSQL规则
    private String csvSql;

    public int getCsvtempId() {
        return csvtempId;
    }

    public void setCsvtempId(int csvtempId) {
        this.csvtempId = csvtempId;
    }

    public int getCsvtempRuleId() {
        return csvtempRuleId;
    }

    public void setCsvtempRuleId(int csvtempRuleId) {
        this.csvtempRuleId = csvtempRuleId;
    }

    public String getCsvSql() {
        return csvSql;
    }

    public void setCsvSql(String csvSql) {
        this.csvSql = csvSql;
    }
}
