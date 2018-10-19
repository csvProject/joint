package com.csv.java.OnlineDataService.impl;





import com.csv.java.OnlineDataService.DataTranceFormService;
import com.csv.java.OnlineDataService.OrderDataMakeService;
import com.csv.java.common.tool.PHPSerializeUtil;
import com.csv.java.common.tool.StringFormatForSQL;
import com.csv.java.dao.GenerateErrorDao;
import com.csv.java.dao.OrderDao;
import com.csv.java.dao.OrderDetailDao;
import com.csv.java.dao.SysCodeDao;
import com.csv.java.entity.GenerateErrorDto;
import com.csv.java.entity.OrderDetailDto;
import com.csv.java.entity.OrderDto;
import com.csv.java.entity.SysCodeDto;
import com.csv.java.net.magja.model.order.Order;
import com.csv.java.net.magja.model.order.OrderItem;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.service.order.OrderRemoteService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private GenerateErrorDao generateErrorDao;


    //生成order信息和orderdetail信息
    @Transactional
    public void makeOrderInfo(OrderRemoteService service,String orderNumber ,int doFlag){

        //获取订单详情
        Order newOrderDetail = new Order();
        try {
            newOrderDetail = service.getById(orderNumber);
        }catch (ServiceException e){
            String errS = "销售订单（"+ orderNumber +"）soap order.info接口数据获取失败:" + e.toString();
            throw new RuntimeException(errS);
        }
        OrderDto orderDto = new OrderDto();

        //网站订单号
        orderDto.setWebsiteorderno(newOrderDetail.getOrderNumber());
        //订货日期
        String createAt = StringFormatForSQL.changeDateFmt(newOrderDetail.getCreatedAt(),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
        orderDto.setDhrq(createAt);

        //支付类型
        String payMethod = newOrderDetail.getOrderPayment().getMethod();
        payMethod = payMethod==null?"":payMethod;
        orderDto.setPaymentid(DataTranceFormService.transformPayMethod(payMethod));

        //订单状态
        String status = newOrderDetail.getStatus()==null?"":newOrderDetail.getStatus();
        int orderStatus = DataTranceFormService.transformStatus(orderDto.getPaymentid(),status);
        orderDto.setOrderstatus(orderStatus);

        //电商地址
        String addressType = newOrderDetail.getShippingAddress().getAddressType();
        addressType  = addressType==null?"":addressType;

        String street = newOrderDetail.getShippingAddress().getStreet();
        street = street==null?"":street;
        String city = newOrderDetail.getShippingAddress().getCity();
        city = city==null?"":city;
        String address = addressType + " " + street + " " + city;
        address = Base64.encodeBase64URLSafeString(address.getBytes());
        orderDto.setAddress(address);

        //收件人客户名
        orderDto.setSqr(newOrderDetail.getShippingAddress().getFirstName() + " "+newOrderDetail.getShippingAddress().getLastName());

        //email或者客户ID
        orderDto.setEmail(newOrderDetail.getCustomerEmail()==null?"":newOrderDetail.getCustomerEmail());

        //电话
        String phone = newOrderDetail.getShippingAddress().getTelephone();
        phone = phone==null?"":phone;
        orderDto.setPhone(phone);

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
            String itemCreateAt = StringFormatForSQL.changeDateFmt(newItem.getCreatedAt(),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
            orderDetailDto.setJoinDate(itemCreateAt);
            //数量
            int qty = newItem.getQtyOrdered()==null?0:(int)Double.parseDouble(newItem.getQtyOrdered().toString());
            orderDetailDto.setQty(qty);
            //客户要求
            String productOptions = newItem.getProductOptions();
            productOptions = productOptions==null?"":PHPSerializeUtil.getProductOptions(productOptions);
            productOptions = Base64.encodeBase64URLSafeString(productOptions.getBytes());
            orderDetailDto.setCustomerRequest(productOptions);
            //明细订单状态
            orderDetailDto.setdOrderStatus(orderStatus);
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
        GenerateErrorDto generateErrorDto =new GenerateErrorDto();
        generateErrorDto.setWebsiteId(3);
        generateErrorDto.setWebsiteOrderNo(orderNumber);
        generateErrorDao.delGenerateError(generateErrorDto);
        //生成数据成功------end

    }

    //更新订单库中订单状态
    @Transactional
    public void updOrderInfo(OrderRemoteService service,OrderDto orderDto ) {
        //获取订单详情
        Order newOrderDetail = new Order();
        try {
            newOrderDetail = service.getById(orderDto.getWebsiteorderno());
        }catch (ServiceException e){
            String errS = "销售订单（"+ orderDto.getWebsiteorderno() +"）soap order.info接口数据获取失败:" + e.toString();
            throw new RuntimeException(errS);
        }
        //订单状态
        String status = newOrderDetail.getStatus()==null?"":newOrderDetail.getStatus();
        int orderStatus = DataTranceFormService.transformStatus(orderDto.getPaymentid(),status);

        //付费状态为已付费才更新
        if (orderStatus == Integer.parseInt(OrderStatusEnum.PAID.toString())) {
            orderDto.setOrderstatus(orderStatus);
            orderDao.updOrderstatusById(orderDto);
            orderDetailDao.updOrderstatusByOrderId(orderDto);
        }
    }


    //子事务test
    @Transactional
    public void maked(int orderNumber){
        //添加此数据到同步失败的网站订单记录表中
        GenerateErrorDto generateErrorDto =new GenerateErrorDto();
        generateErrorDto.setWebsiteId(3);
        generateErrorDto.setWebsiteOrderNo("A"+orderNumber);
        generateErrorDto.setErrorInfo("OK"+orderNumber);

        generateErrorDao.insertGenerateError(generateErrorDto);

        GenerateErrorDto generateErrorDto2 =new GenerateErrorDto();
        generateErrorDto2.setWebsiteId(3);
        generateErrorDto2.setWebsiteOrderNo("B"+orderNumber);
        generateErrorDto2.setErrorInfo("OK"+orderNumber);
        if (orderNumber == 1003 || orderNumber == 1005) {
            int qty = Integer.parseInt("1.0");
        }
        generateErrorDao.insertGenerateError(generateErrorDto2);

    }
}