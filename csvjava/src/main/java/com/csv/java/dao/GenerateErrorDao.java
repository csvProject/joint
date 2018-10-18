package com.csv.java.dao;





import com.csv.java.entity.GenerateErrorDto;

import java.util.List;

public interface GenerateErrorDao {

    //查询同步出错网站订单号
    List<GenerateErrorDto> findErrOrderNo(GenerateErrorDto into);

    //添加出错订单
    public void  insertGenerateError(GenerateErrorDto into);

    //删除
    public void delGenerateError(GenerateErrorDto indto);

    //逻辑删除
    public void updDelFlag(int generateErrorId);
}
