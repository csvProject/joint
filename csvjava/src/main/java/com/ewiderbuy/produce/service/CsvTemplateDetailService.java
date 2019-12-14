package com.ewiderbuy.produce.service;






import com.ewiderbuy.produce.entity.CsvTempBatDto;
import com.ewiderbuy.produce.entity.CsvTemplateDetailDto;

import java.util.List;

public interface CsvTemplateDetailService {

    List<CsvTemplateDetailDto> findCsvTempDetailBycsvtempId(int csvtempId);

    List<CsvTemplateDetailDto> findCsvTempDetailByKey(CsvTemplateDetailDto indto);

    CsvTemplateDetailDto findCsvTempDetailById(int csvFieldId);

    public void updCsvTempDetailById(CsvTemplateDetailDto indto);

    public void insertCsvTempDetail(CsvTemplateDetailDto indto);

    public void delCsvTempDetailById(int csvFieldId);

    public void updCsvTempDetailBat(CsvTempBatDto indto);

}
