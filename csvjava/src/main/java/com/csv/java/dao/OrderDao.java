package com.csv.java.dao;


import com.csv.java.entity.OrderDto;

import java.util.List;

public interface OrderDao {
    //查询所有未付款订单
    List<OrderDto> findOrderNulPay(int websiteid);

    //获取最新网站订单号
    public int  getLastWebsiteorderno(int websiteid);

    //更新订单状态
    public void updOrderstatusByWebsiteorderno(OrderDto indto);

    //订单添加
    public void insertOrderInfo(OrderDto indto);
}
