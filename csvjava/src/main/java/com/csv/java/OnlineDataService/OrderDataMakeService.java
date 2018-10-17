package com.csv.java.OnlineDataService;


import com.csv.java.entity.OrderDto;
import com.csv.java.net.magja.service.order.OrderRemoteService;

public interface OrderDataMakeService {

    //生成order信息和orderdetail信息,doFlag为1同步新增用，为2同步数据错误记录表的处理
    public void makeOrderInfo(OrderRemoteService service, String orderNumber , int doFlag);

    //更新订单库中订单状态
    public void updOrderInfo(OrderRemoteService service, OrderDto orderDto);

    public void maked(int orderNumber);

}
