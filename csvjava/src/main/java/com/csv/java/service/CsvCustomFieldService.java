package com.csv.java.service;



import com.csv.java.entity.CsvCustomFieldBatDto;
import com.csv.java.entity.CsvCustomFieldDto;
import com.csv.java.entity.CsvTemplateDetailDto;

import java.util.List;

public interface CsvCustomFieldService {
    List<CsvCustomFieldDto> findCsvCustomField(int csvtempId);

    public void updCsvCustomFieldBat(CsvCustomFieldBatDto indto);

    public Boolean verifyFormula(String formula);

    public  List<CsvTemplateDetailDto> chkDelCustomField(CsvCustomFieldDto indto);
}
