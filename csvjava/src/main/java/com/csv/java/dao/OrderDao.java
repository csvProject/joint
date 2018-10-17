package com.csv.java.dao;


import com.csv.java.entity.OrderCondiInDto;
import com.csv.java.entity.OrderDto;

import java.util.List;

public interface OrderDao {
    //查询所有未付款订单
    List<OrderDto> findOrderNulPay(OrderCondiInDto indto);

    //更新订单状态
    public void updOrderstatusById(OrderDto indto);

    //订单添加
    public void insertOrderInfo(OrderDto indto);
}
