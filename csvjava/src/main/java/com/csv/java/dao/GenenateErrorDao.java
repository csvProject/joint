package com.csv.java.dao;





import com.csv.java.entity.GenenateErrorDto;

import java.util.List;

public interface GenenateErrorDao {

    //查询同步出错网站订单号
    List<GenenateErrorDto> findErrOrderNo(int websiteId);

    //添加出错订单
    public void  insertGenenateError(GenenateErrorDto into);

    //删除
    public void delGenenateError(GenenateErrorDto indto);
}
