package com.csv.java.service;






import com.csv.java.entity.CsvTempBatDto;
import com.csv.java.entity.CsvTemplateDetailDto;

import java.util.List;

public interface CsvTemplateDetailService {
    List<CsvTemplateDetailDto> findCsvTempDetailByKey(CsvTemplateDetailDto indto);

    CsvTemplateDetailDto findCsvTempDetailById(int csvFieldId);

    public void updCsvTempDetailById(CsvTemplateDetailDto indto);

    public void insertCsvTempDetail(CsvTemplateDetailDto indto);

    public void delCsvTempDetailById(int csvFieldId);

    public void updCsvTempDetailBat(CsvTempBatDto indto);

}
