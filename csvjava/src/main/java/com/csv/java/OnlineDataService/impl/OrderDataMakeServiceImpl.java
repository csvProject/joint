package com.csv.java.OnlineDataService.impl;





import com.csv.java.OnlineDataService.OrderDataMakeService;
import com.csv.java.common.tool.PHPSerializeUtil;
import com.csv.java.dao.GenenateErrorDao;
import com.csv.java.dao.OrderDao;
import com.csv.java.dao.OrderDetailDao;
import com.csv.java.dao.SysCodeDao;
import com.csv.java.entity.GenenateErrorDto;
import com.csv.java.entity.OrderDetailDto;
import com.csv.java.entity.OrderDto;
import com.csv.java.entity.SysCodeDto;
import com.csv.java.net.magja.model.order.Order;
import com.csv.java.net.magja.model.order.OrderItem;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.service.order.OrderRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.csv.java.OnlineDataService.ConstantDataService.*;

@Service(value = "orderDataMakeService")
public class OrderDataMakeServiceImpl implements OrderDataMakeService {

    @Autowired
    private OrderDao  orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private SysCodeDao sysCodeDao;

    @Autowired
    private GenenateErrorDao genenateErrorDao;


    //生成order信息和orderdetail信息
    @Transactional
    public void makeOrderInfo(OrderRemoteService service,String orderNumber ,int doFlag){

            //获取订单详情
        Order newOrderDetail = new Order();
        try {
            newOrderDetail = service.getById(orderNumber);
        }catch (ServiceException e){

        }
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
        String status = newOrderDetail.getStatus()==null?"":newOrderDetail.getStatus();
        String orderStatus = "";
        if (status.equals(StatusEnum.CANCELED.toString()) ) {
            //是canceled状态，无论哪种付费方式，都为-1
            orderStatus = OrderStatusEnum.CANCELED.toString();
        }else {
            if (orderDto.getPaymentid() == Integer.parseInt(PaymentidEnum.Bank.toString())) {
                //银行入金付费完成由订单系统去更新，从销售库过来直接对应6
                orderStatus = OrderStatusEnum.UNPAID.toString();
            } else {
                //信用卡或paypal的时候，如果是processing订单库对应1 其他都是6
                if (status.equals(StatusEnum.PROCESSING.toString())) {
                    orderStatus = OrderStatusEnum.PAID.toString();
                } else {
                    orderStatus = OrderStatusEnum.UNPAID.toString();
                }
            }
        }
        orderDto.setOrderstatus(Integer.parseInt(orderStatus));

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
        orderDto.setContents("");
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
            List<String> reusltList = PHPSerializeUtil.unserializePHParray(productOptions);
            productOptions = reusltList.toString();
            orderDetailDto.setCustomerRequest(productOptions);
            //明细订单状态
            orderDetailDto.setdOrderStatus(Integer.parseInt(orderStatus));
            //紧急状态ID
            orderDetailDto.setPropertyId(4);
            //管理ID
            orderDetailDto.setMgrId(2);
            //仓库出库标识
            orderDetailDto.setWhw(0);
            //订单明细添加
            orderDetailDao.insertOrderDetailInfo(orderDetailDto);
        }

        //生成数据成功------start
        if (doFlag == 1) {
            //失败网站订单不需要更新
            //更新字典表配置的同步的网站订单id
            SysCodeDto sysCodeDto = new SysCodeDto();
            sysCodeDto.setSysTypeCd(3);
            sysCodeDto.setSysCd("1");
            sysCodeDto.setSysNm(orderNumber);
            sysCodeDao.updSysNm(sysCodeDto);
        }
        //删除同步失败的网站订单记录表中记录
        GenenateErrorDto genenateErrorDto =new GenenateErrorDto();
        genenateErrorDto.setWebsiteId(3);
        genenateErrorDto.setWebsiteOrderNo(orderNumber);
        genenateErrorDao.delGenenateError(genenateErrorDto);
        //生成数据成功------end


    }

    //子事务test
    @Transactional
    public void maked(int orderNumber){
        //添加此数据到同步失败的网站订单记录表中
        GenenateErrorDto genenateErrorDto =new GenenateErrorDto();
        genenateErrorDto.setWebsiteId(3);
        genenateErrorDto.setWebsiteOrderNo("A"+orderNumber);
        genenateErrorDto.setErrorInfo("OK"+orderNumber);

        genenateErrorDao.insertGenenateError(genenateErrorDto);

        GenenateErrorDto genenateErrorDto2 =new GenenateErrorDto();
        genenateErrorDto2.setWebsiteId(3);
        genenateErrorDto2.setWebsiteOrderNo("B"+orderNumber);
        genenateErrorDto2.setErrorInfo("OK"+orderNumber);
        if (orderNumber == 1003 || orderNumber == 1005) {
            String a = "aa";
            int b = Integer.parseInt(a);
        }
        genenateErrorDao.insertGenenateError(genenateErrorDto2);

    }
}
