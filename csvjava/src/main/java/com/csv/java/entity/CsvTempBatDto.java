package com.csv.java.entity;

//模板字段dto
public class CsvTempBatDto extends BaseDto{
    //CSV模板字段ID
    private int csvFieldId;
    //CSV模板ID
    private int csvtempId;
    //模板字段key
    private String fieldKey;
    //模板字段key描述
    private String fieldNm;
    //模板字段value
    private String fieldValue;
    //模板字段类别
    private String fieldType;
    //模板字段顺序
    private int fieldSort;

    public int getCsvFieldId() {
        return csvFieldId;
    }

    public void setCsvFieldId(int csvFieldId) {
        this.csvFieldId = csvFieldId;
    }

    public int getCsvtempId() {
        return csvtempId;
    }

    public void setCsvtempId(int csvtempId) {
        this.csvtempId = csvtempId;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldNm() {
        return fieldNm;
    }

    public void setFieldNm(String fieldNm) {
        this.fieldNm = fieldNm;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(int fieldSort) {
        this.fieldSort = fieldSort;
    }
}
