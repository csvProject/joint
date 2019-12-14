package com.ewiderbuy.produce.service;






import com.ewiderbuy.produce.entity.CsvTemplateInfoDto;

import java.util.List;

public interface CsvTemplateInfoService {
    List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto);

    CsvTemplateInfoDto findCsvTempInfoById(int csvtempId);

    public int updCsvTempInfoById(CsvTemplateInfoDto indto);

    public int insertCsvTempInfo(CsvTemplateInfoDto indto);

    public void delCsvTempInfoById(int csvtempId);

    public int copyCsvTempInfo(CsvTemplateInfoDto indto);
}
