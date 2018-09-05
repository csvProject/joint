package com.csv.java.dao;





import com.csv.java.entity.CsvCustmerFieldDto;

import java.util.List;

public interface CsvCustmerFieldDao {

    //查询所有的自定义公式
    List<CsvCustmerFieldDto> findCsvCustmerField(int csvtempId);

    //更新自定义字段内容
    public void updCustmerFieldById(CsvCustmerFieldDto into);

    //删除模板下所有自定义字段
    public void delCustmerFieldBycsvtempId(int csvtempId);
}
