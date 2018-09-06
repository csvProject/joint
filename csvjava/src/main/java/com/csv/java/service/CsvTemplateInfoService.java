package com.csv.java.service;






import com.csv.java.entity.CsvTemplateInfoDto;

import java.util.List;

public interface CsvTemplateInfoService {
    List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto);

    CsvTemplateInfoDto findCsvTempInfoById(int csvtempId);

    public int updCsvTempInfoById(CsvTemplateInfoDto indto);

    public int insertCsvTempInfo(CsvTemplateInfoDto indto);

    public void delCsvTempInfoById(int csvtempId);
}
