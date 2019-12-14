package com.ewiderbuy.produce.dao;


import com.ewiderbuy.produce.entity.OrderDetailDto;
import com.ewiderbuy.produce.entity.OrderDto;

import java.util.List;

public interface OrderDetailDao {

    //订单明细添加
    public void insertOrderDetailInfo(OrderDetailDto indto);

    //更新订单状态
    public void updOrderstatusByOrderId(OrderDto indto);
}
