package com.csv.java.entity;

//模板自定义字段dto
public class CsvCustomFieldDto extends BaseDto{
    //模板自定义字段ID
    private int csvCustomFieldId;
    //模板ID
    private int csvtempId;
    //自定义字段类型
    private int cfieldType;
    //自定义字段名称
    private String cfieldNm;
    //自定义字段内容
    private String cfieldValue;

    public int getCsvCustomFieldId() {
        return csvCustomFieldId;
    }

    public void setCsvCustomFieldId(int csvCustomFieldId) {
        this.csvCustomFieldId = csvCustomFieldId;
    }

    public int getCsvtempId() {
        return csvtempId;
    }

    public void setCsvtempId(int csvtempId) {
        this.csvtempId = csvtempId;
    }

    public int getCfieldType() {
        return cfieldType;
    }

    public void setCfieldType(int cfieldType) {
        this.cfieldType = cfieldType;
    }

    public String getCfieldNm() {
        return cfieldNm;
    }

    public void setCfieldNm(String cfieldNm) {
        this.cfieldNm = cfieldNm;
    }

    public String getCfieldValue() {
        return cfieldValue;
    }

    public void setCfieldValue(String cfieldValue) {
        this.cfieldValue = cfieldValue;
    }
}
