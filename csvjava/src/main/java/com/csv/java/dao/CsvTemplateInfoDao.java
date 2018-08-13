package com.csv.java.dao;





import com.csv.java.entity.CsvTemplateInfoDto;

import java.util.List;

public interface CsvTemplateInfoDao {
    //根据模板ID查询
    CsvTemplateInfoDto findCsvTempInfoById(int csvtempId);

    //多条件查询
    List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto);

    //模板信息更新
    public void updCsvTempInfoById(CsvTemplateInfoDto indto);

    //模板添加
    public void insertCsvTempInfo(CsvTemplateInfoDto indto);

    //删除模板
    public void delCsvTempInfoById(int csvtempId);


}
