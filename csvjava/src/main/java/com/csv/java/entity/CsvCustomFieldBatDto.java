package com.csv.java.entity;

import java.util.List;

//模板字段dto
public class CsvCustomFieldBatDto extends BaseDto {

    //CSV模板ID
    private int csvtempId;

    //csv自定义字段dto
    private List<CsvCustomFieldDto> csvCustomFieldDtoList;

    public int getCsvtempId() {
        return csvtempId;
    }

    public void setCsvtempId(int csvtempId) {
        this.csvtempId = csvtempId;
    }

    public List<CsvCustomFieldDto> getCsvCustomFieldDtoList() {
        return csvCustomFieldDtoList;
    }

    public void setCsvCustomFieldDtoList(List<CsvCustomFieldDto> csvCustomFieldDtoList) {
        this.csvCustomFieldDtoList = csvCustomFieldDtoList;
    }
}
