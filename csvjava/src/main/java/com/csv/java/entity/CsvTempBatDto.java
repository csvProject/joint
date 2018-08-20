package com.csv.java.entity;

import java.util.List;

//模板字段dto
public class CsvTempBatDto extends BaseDto {

    //CSV模板ID
    private int csvtempId;

    //csv字段dto
    private List<CsvTemplateDetailDto> csvTemplateDetailDtoList;

    public int getCsvtempId() {
        return csvtempId;
    }

    public void setCsvtempId(int csvtempId) {
        this.csvtempId = csvtempId;
    }

    public List<CsvTemplateDetailDto> getCsvTemplateDetailDtoList() {
        return csvTemplateDetailDtoList;
    }

    public void setCsvTemplateDetailDtoList(List<CsvTemplateDetailDto> csvTemplateDetailDtoList) {
        this.csvTemplateDetailDtoList = csvTemplateDetailDtoList;
    }
}
