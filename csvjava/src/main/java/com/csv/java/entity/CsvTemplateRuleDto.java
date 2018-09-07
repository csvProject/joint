package com.csv.java.entity;

//模板规则dto
public class CsvTemplateRuleDto extends BaseDto{
    //CSV模板ID
    private int csvtempId;

    //模板规则ID
    private int csvtempRuleId;

    //CSVSQL规则
    private String csvSql;


    //是否显示头部
    private Integer headShow;

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

    public Integer getHeadShow() {
        return headShow;
    }

    public void setHeadShow(Integer headShow) {
        this.headShow = headShow;
    }
}
