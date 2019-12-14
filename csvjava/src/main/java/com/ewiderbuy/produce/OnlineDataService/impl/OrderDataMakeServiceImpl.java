package com.ewiderbuy.produce.OnlineDataService.impl;





import com.ewiderbuy.produce.OnlineDataService.DataTranceFormService;
import com.ewiderbuy.produce.OnlineDataService.OrderDataMakeService;
import com.ewiderbuy.produce.common.tool.DateUtil;
import com.ewiderbuy.produce.OnlineDataService.PHPSerializeUtil;
import com.ewiderbuy.produce.common.tool.StringFormatForSQL;
import com.ewiderbuy.produce.dao.GenerateErrorDao;
import com.ewiderbuy.produce.dao.OrderDao;
import com.ewiderbuy.produce.dao.OrderDetailDao;
import com.ewiderbuy.produce.dao.SysCodeDao;
import com.ewiderbuy.produce.entity.GenerateErrorDto;
import com.ewiderbuy.produce.entity.OrderDetailDto;
import com.ewiderbuy.produce.entity.OrderDto;
import com.ewiderbuy.produce.entity.SysCodeDto;
import com.ewiderbuy.produce.net.magja.model.order.Order;
import com.ewiderbuy.produce.net.magja.model.order.OrderItem;
import com.ewiderbuy.produce.net.magja.model.order.OrderStatusHistory;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.service.order.OrderRemoteService;
import com.ewiderbuy.produce.OnlineDataService.ConstantDataService;
import com.ewiderbuy.produce.OnlineDataService.DataTranceFormService;
import com.ewiderbuy.produce.OnlineDataService.PHPSerializeUtil;
import com.ewiderbuy.produce.common.tool.StringFormatForSQL;
import com.ewiderbuy.produce.dao.GenerateErrorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.ewiderbuy.produce.OnlineDataService.ConstantDataService.*;

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
            String errS = DateUtil.date2String(new Date(),"yyyy-MM-dd HH:mm:ss") +
                    "销售订单【"+ orderNumber +"】soap order.info接口数据获取失败:" + e.toString();
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
        int orderStatus = DataTranceFormService.transformStatus(orderDto,status);
        orderDto.setOrderstatus(orderStatus);

        //电商地址
        String street = newOrderDetail.getShippingAddress().getStreet();
        street = street==null?"":street.replace("\n","");
        String city = newOrderDetail.getShippingAddress().getCity();
        city = city==null?"":city;
        String region = newOrderDetail.getShippingAddress().getRegion();
        region = region ==null?"":region;

        String address = region + " " + city + " " +street;
        address = DataTranceFormService.transformPHPencode(address);
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
        orderDto.setCurrency("");

        //网站ID
        orderDto.setWebsiteid(3);

        //备注
        String contents = "";
        for ( int i =0,len = newOrderDetail.getOrderStatusHistories().size();i < len;i++) {
            OrderStatusHistory orderStatusHistory = newOrderDetail.getOrderStatusHistories().get(i);
            String comment = orderStatusHistory.getComment() == null?"":orderStatusHistory.getComment();
            if (!"".equals(comment)) {
                contents = orderStatusHistory.getCreatedAt() + " " +
                        comment + ";" + contents;
            }
        }
        contents = DataTranceFormService.transformPHPencode(contents);
        orderDto.setContents(contents);

        //国家ID
        orderDto.setCountryid(2);

        //管理ID
        orderDto.setMgrid(1);

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

            //数量
            int qty = newItem.getQtyOrdered()==null?0:(int)Double.parseDouble(newItem.getQtyOrdered().toString());
            orderDetailDto.setQty(qty);
            //客户要求
            String productOptions = newItem.getProductOptions();
            productOptions = productOptions==null?"": PHPSerializeUtil.getProductOptions(productOptions);
            productOptions = DataTranceFormService.transformPHPencode(productOptions);
            orderDetailDto.setCustomerRequest(productOptions);
            //尺寸ID
            int sizeId = PHPSerializeUtil.getSizeId(newItem.getProductOptions());
            orderDetailDto.setSizeId(sizeId);
            //明细订单状态
            orderDetailDto.setdOrderStatus(orderStatus);
            //紧急状态ID
            orderDetailDto.setPropertyId(4);
            //管理ID
            orderDetailDto.setMgrId(1);
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
            String errS = DateUtil.date2String(new Date(),"yyyy-MM-dd HH:mm:ss") +
                    "销售订单【"+ orderDto.getWebsiteorderno() +"】soap order.info接口数据获取失败:" + e.toString();
            throw new RuntimeException(errS);
        }
        //订单状态
        String status = newOrderDetail.getStatus()==null?"":newOrderDetail.getStatus();
        int orderStatus = DataTranceFormService.transformStatus(orderDto,status);

        //付费状态为已付费才更新
        if (orderStatus == Integer.parseInt(ConstantDataService.OrderStatusEnum.PAID.toString())) {
            orderDto.setOrderstatus(orderStatus);
            orderDao.updOrderstatusById(orderDto);
            orderDetailDao.updOrderstatusByOrderId(orderDto);
            System.out.println(DateUtil.date2String(new Date(),"yyyy-MM-dd HH:mm:ss") +
                    "销售订单【" + orderDto.getWebsiteorderno() + "】更新支付状态成功");
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
