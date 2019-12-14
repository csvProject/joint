package com.ewiderbuy.produce.service;



import com.ewiderbuy.produce.entity.CsvCustomFieldBatDto;
import com.ewiderbuy.produce.entity.CsvCustomFieldDto;
import com.ewiderbuy.produce.entity.CsvTemplateDetailDto;
import com.ewiderbuy.produce.entity.CsvCustomFieldDto;

import java.util.List;

public interface CsvCustomFieldService {
    List<CsvCustomFieldDto> findCsvCustomField(int csvtempId);

    public void updCsvCustomFieldBat(CsvCustomFieldBatDto indto);

    public Boolean verifyFormula(String formula);

    public  List<CsvTemplateDetailDto> chkDelCustomField(CsvCustomFieldDto indto);
}
