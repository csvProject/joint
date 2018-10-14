package com.csv.java.dao;


import com.csv.java.entity.OrderDetailDto;

import java.util.List;

public interface OrderDetailDao {

    //订单明细添加
    public void insertOrderDetailInfo(OrderDetailDto indto);
}
