package com.ewiderbuy.produce.dao;





import com.ewiderbuy.produce.entity.CsvTemplateInfoDto;

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

    //判断同一平台，账号，商品类型，供应商下是否存在模板,结果大于0则表示存在
    public int checkCsvTempInfoOnly(CsvTemplateInfoDto indto);

    //判断更新时模板名称是否相同,结果大于0则表示存在
    public int checkCsvTempNmOnly(CsvTemplateInfoDto indto);

    //判断添加时模板名称是否相同,结果大于0则表示存在
    public int chkAddCsvTempNmOnly(String csvtempNm);
}
