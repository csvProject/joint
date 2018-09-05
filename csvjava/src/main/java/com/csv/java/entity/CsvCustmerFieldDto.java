package com.csv.java.entity;

//模板自定义字段dto
public class CsvCustmerFieldDto extends BaseDto{
    //模板自定义字段ID
    private int csvCustmerFieldId;
    //模板ID
    private int csvtempId;
    //自定义字段类型
    private int cfieldType;
    //自定义字段名称
    private String cfieldNm;
    //自定义字段内容
    private String cfieldValue;

    public int getCsvCustmerFieldId() {
        return csvCustmerFieldId;
    }

    public void setCsvCustmerFieldId(int csvCustmerFieldId) {
        this.csvCustmerFieldId = csvCustmerFieldId;
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
