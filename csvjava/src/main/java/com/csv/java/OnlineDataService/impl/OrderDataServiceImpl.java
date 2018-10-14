package com.csv.java.OnlineDataService.impl;





import com.csv.java.OnlineDataService.ConstantDataService;
import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.dao.OrderDao;
import com.csv.java.dao.OrderDetailDao;
import com.csv.java.entity.OrderDetailDto;
import com.csv.java.entity.OrderDto;
import com.csv.java.net.magja.model.order.Filter;
import com.csv.java.net.magja.model.order.FilterItem;
import com.csv.java.net.magja.model.order.Order;
import com.csv.java.net.magja.model.order.OrderItem;
import com.csv.java.net.magja.service.RemoteServiceFactory;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.service.order.OrderRemoteService;
import com.csv.java.net.magja.soap.MagentoSoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.csv.java.OnlineDataService.ConstantDataService.*;

@Service(value = "orderDataService")
public class OrderDataServiceImpl implements OrderDataService {

    @Autowired
    private OrderDao  orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    private OrderRemoteService service;

    //获取新的订单及更新已有未付款订单的状态
    public void GenenateOrderDataFromMagento(){

        final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
        service = remoteServiceFactory.getOrderRemoteService();

        //获取新的订单添加到库
        int lastIncrementId = orderDao.getLastWebsiteorderno(3);
        Filter filter = new Filter();
        filter.getItems().add(new FilterItem("increment_id", ">", lastIncrementId+""));

        try{
            List<Order> newOrderList = service.list(filter);
            for (Order newOrder : newOrderList) {
                this.makeOrderInfo(newOrder);
            }
        }catch (ServiceException e){
            System.out.println(e.toString());
        }

    }

    //生成order信息和orderdetail信息
    @Transactional
    public void makeOrderInfo(Order newOrder){
        try{
            //获取订单详情
            Order newOrderDetail = service.getById(newOrder.getOrderNumber());

            OrderDto orderDto = new OrderDto();

            //网站订单号
            orderDto.setWebsiteorderno(newOrderDetail.getOrderNumber());
            //订货日期
            orderDto.setDhrq(newOrderDetail.getCreatedAt());

            //支付类型
            String payMethod = newOrderDetail.getOrderPayment().getMethod();
            payMethod = payMethod==null?"":payMethod;
            //paypal支付
            if (payMethod.equals(PayMethodEnum.PAYPAL_STANDARD.toString())){
                orderDto.setPaymentid(Integer.parseInt(PaymentidEnum.PAYPAL.toString()));
            }
            //银行入金
            if (payMethod.equals(PayMethodEnum.CHECKMO.toString())){
                orderDto.setPaymentid(Integer.parseInt(PaymentidEnum.Bank.toString()));
            }
            //信用卡
            if (payMethod.equals(PayMethodEnum.MASAPAY_PAYMENT.toString())){
                orderDto.setPaymentid(Integer.parseInt(PaymentidEnum.CREDIT_CARD.toString()));
            }

            //订单状态
            String status = newOrder.getStatus()==null?"":newOrder.getStatus();

            if (status.equals(StatusEnum.CANCELED.toString()) ) {
                //是canceled状态，无论哪种付费方式，都为-1
                orderDto.setOrderstatus(Integer.parseInt(OrderStatusEnum.CANCELED.toString()));
            }else {
                if (orderDto.getPaymentid() == Integer.parseInt(PaymentidEnum.Bank.toString())) {
                    //银行入金付费完成由订单系统去更新，从销售库过来直接对应6
                    orderDto.setOrderstatus(Integer.parseInt(OrderStatusEnum.UNPAID.toString()));
                } else {
                    //信用卡或paypal的时候，如果是processing订单库对应1 其他都是6
                    if (status.equals(StatusEnum.PROCESSING.toString())) {
                        orderDto.setOrderstatus(Integer.parseInt(OrderStatusEnum.PAID.toString()));
                    } else {
                        orderDto.setOrderstatus(Integer.parseInt(OrderStatusEnum.UNPAID.toString()));
                    }
                }
            }

            //电商地址
            String addressType = newOrderDetail.getShippingAddress().getAddressType();
            addressType  = addressType==null?"":addressType;

            String street = newOrderDetail.getShippingAddress().getStreet();
            street = street==null?"":street;
            String city = newOrderDetail.getShippingAddress().getCity();
            city = city==null?"":city;
            orderDto.setAddress(addressType + " " + street + " " + city);

            //收件人客户名
            orderDto.setSqr(newOrderDetail.getShippingAddress().getFirstName() + " "+newOrderDetail.getShippingAddress().getLastName());

            //email或者客户ID
            orderDto.setEmail(newOrderDetail.getCustomerEmail()==null?"":newOrderDetail.getCustomerEmail());

            //邮政编码
            String postCode = newOrderDetail.getShippingAddress().getPostCode();
            postCode = postCode ==null?"":postCode;
            orderDto.setZipcode(postCode);

            //订单价格
            orderDto.setTotalprice(newOrderDetail.getBaseGrandTotal());

            //币种
            orderDto.setCurrency(newOrderDetail.getBaseCurrencyCode());

            //网站ID
            orderDto.setWebsiteid(3);

            //备注

            //国家ID
            orderDto.setCountryid(2);

            //管理ID
            orderDto.setMgrid(2);

            //添加订单
            orderDao.insertOrderInfo(orderDto);

            for ( int i =0,len = newOrderDetail.getItems().size();i < len;i++) {
                OrderItem newItem = newOrderDetail.getItems().get(i);
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                //订单号
                orderDetailDto.setOrderId(orderDto.getOrderId());
                //订单编号
                if (len == 1){
                    orderDetailDto.setdOrderNo("D03-" + orderDto.getOrderId());
                }else{
                    String strN = String.format("%02d", i+1);
                    orderDetailDto.setdOrderNo("D03-" + orderDto.getOrderId() + "-" + strN);
                }
                //SKU
                orderDetailDto.setSku(newItem.getSku()==null?"":newItem.getSku());
                //尺寸ID
                orderDetailDto.setSizeId(0);
                //录入日期
                orderDetailDto.setJoinDate(newItem.getCreatedAt());
                //数量
                int qty = newItem.getQtyOrdered()==null?0:Integer.parseInt(newItem.getQtyOrdered().toString());
                orderDetailDto.setQty(qty);
                //客户要求
                String productOptions = newItem.getProductOptions();
                productOptions = productOptions==null?"":productOptions;
                orderDetailDto.setCustomerRequest(productOptions);
                //明细订单状态
                orderDetailDto.setdOrderStatus(1);
                //紧急状态ID
                orderDetailDto.setPropertyId(4);
                //管理ID
                orderDetailDto.setMgrId(2);
                //仓库出库标识
                orderDetailDto.setWhw(0);
                //订单明细添加
                orderDetailDao.insertOrderDetailInfo(orderDetailDto);
            }
        }catch (ServiceException e){
            System.out.println(e.toString());
        }

    }
}
