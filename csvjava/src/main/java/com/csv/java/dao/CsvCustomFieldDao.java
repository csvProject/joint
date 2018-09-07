package com.csv.java.dao;





import com.csv.java.entity.CsvCustomFieldDto;

import java.util.List;

public interface CsvCustomFieldDao {

    //查询所有的自定义公式
    List<CsvCustomFieldDto> findCsvCustomField(int csvtempId);

    //更新自定义字段内容
    public void updCustomFieldById(CsvCustomFieldDto into);

    //删除模板下所有自定义字段
    public void delCustomFieldBycsvtempId(int csvtempId);

    //自定义模板字段添加
    public void insertCustomField(CsvCustomFieldDto indto);
}