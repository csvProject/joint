package com.csv.java.dao;







import com.csv.java.entity.CsvTemplateDetailDto;

import java.util.List;

public interface CsvTemplateDetailDao {
    //根据ID查询
    CsvTemplateDetailDto findCsvTempDetailById(int csvFieldId);

    //根据字段名称查询
    List<CsvTemplateDetailDto> findCsvTempDetailByKey(CsvTemplateDetailDto indto);

    //字段更新
    public void updCsvTempDetailById(CsvTemplateDetailDto indto);

    //模板字段添加
    public void insertCsvTempDetail(CsvTemplateDetailDto indto);

    //删除字段
    public void delCsvTempDetailById(int csvFieldId);

    //删除模板所有字段
    public void delCsvTempDetailBycsvtempId(int csvtempId);

}
