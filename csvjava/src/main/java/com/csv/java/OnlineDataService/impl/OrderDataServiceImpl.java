package com.csv.java.OnlineDataService.impl;





import com.csv.java.service.GenerateErrorService;
import com.csv.java.OnlineDataService.OrderDataMakeService;
import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.common.tool.DateUtil;
import com.csv.java.dao.GenerateErrorDao;
import com.csv.java.dao.OrderDao;
import com.csv.java.dao.SysCodeDao;
import com.csv.java.entity.GenerateErrorDto;
import com.csv.java.entity.OrderCondiInDto;
import com.csv.java.entity.OrderDto;
import com.csv.java.entity.SysCodeDto;
import com.csv.java.net.magja.model.order.Filter;
import com.csv.java.net.magja.model.order.FilterItem;
import com.csv.java.net.magja.model.order.Order;
import com.csv.java.net.magja.service.RemoteServiceFactory;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.service.order.OrderRemoteService;
import com.csv.java.net.magja.soap.MagentoSoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.csv.java.config.ConstantConfig.ORDERDATA_INTERVAL_NDAY;

@Service(value = "orderDataService")
public class OrderDataServiceImpl implements OrderDataService {

    @Autowired
    private GenerateErrorService generateErrorService;

    @Autowired
    private OrderDataMakeService orderDataMakeService;

    @Autowired
    private SysCodeDao sysCodeDao;

    @Autowired
    private GenerateErrorDao generateErrorDao;

    @Autowired
    private OrderDao orderDao;

    private OrderRemoteService service;

    //获取新的订单及更新已有未付款订单的状态
    public void GenerateOrderDataFromMagento(){

        System.out.println("定时数据同步开始++++++++++++++++++++");
        final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
        service = remoteServiceFactory.getOrderRemoteService();

        //本次处理产生的失败订单号
        List<String> errOrderList = new ArrayList();

        //获取新的订单添加到库
        SysCodeDto sysCodeDto = new SysCodeDto();
        sysCodeDto.setSysTypeCd(3);
        sysCodeDto.setSysCd("1");
        SysCodeDto forIncrementId = sysCodeDao.findSysCodeBySysCd(sysCodeDto);
        Filter filter = new Filter();
        filter.getItems().add(new FilterItem("increment_id", "gt", forIncrementId.getSysNm()+""));

        /* 数据同步策略，下记每一点为一个事务
         * 1.把销售订单同步到订单系统，再记下最后同步成功的销售订单号，下一次同步时，同步销售订单条件为 大于 最后同步成功的销售订单号
         * 2.销售订单同步时，每条销售订单的同步处理为单独一个事务，当条销售订单同步报错，记录销售订单号及错误信息到同步错误记录表中，
         * 继续下一个销售订单号的同步；如果记录到错误记录表时出错，则后面所有的销售订单不同步
         * 3.扫描错误记录表，把表中的销售订单进行再同步
         * 4.订单状态更新中每条订单的处理为单独事务*/
        try{
            List<Order> newOrderList = new ArrayList<Order>();
            try {
                newOrderList = service.list(filter);
            }catch (ServiceException e){
                throw new RuntimeException("订单（销售订单号 > "+ forIncrementId.getSysNm() +"）soap order.list接口数据获取失败");
            }

            for (Order newOrder : newOrderList) {
                String err ="";

                try {
                    //销售订单同步到订单系统
                    orderDataMakeService.makeOrderInfo(service, newOrder.getOrderNumber(), 1);
                }catch (Exception e){
                    System.out.println(e.toString());
                    err =e.toString();
                }
                if (!"".equals(err)){
                    System.out.println("销售订单（"+ newOrder.getOrderNumber() +"）同步失败，登记到同步错误记录表");
                    //同步错误的销售订单记录到同步错误记录表中
                    generateErrorService.addGenerateError(newOrder.getOrderNumber(),err);
                    errOrderList.add(newOrder.getOrderNumber());
                }
            }
        }catch (Exception e){
            System.out.println("同步数据处理异常中断！");
            System.out.println(e.toString());
        }

        //之前同步失败的同步错误记录表中订单在每次继续检查同步
        List<GenerateErrorDto> generateErrorDtoList = generateErrorDao.findErrOrderNo(3);
        for (GenerateErrorDto generateErrorDto : generateErrorDtoList) {
            boolean hav = false;
            //本次同步处理产生失败订单不需要进行再同步
            for (String errOrder : errOrderList){
                if (errOrder.equals(generateErrorDto.getWebsiteOrderNo())){
                    hav = true;
                    break;
                }
            }
            if (!hav) {
                try {
                    orderDataMakeService.makeOrderInfo(service, generateErrorDto.getWebsiteOrderNo(), 2);
                } catch (Exception e) {
                    System.out.println("同步错误记录表的销售订单（"+ generateErrorDto.getWebsiteOrderNo() +"）再同步失败");
                    System.out.println(e.toString());
                }
            }
        }
        System.out.println("定时数据同步结束++++++++++++++++++++");

        System.out.println("定时订单支付状态更新开始#################");
        /* 订单状态更新
        1.获取订单系统中所有未付款的订单
        2.查询对应销售订单，更新订单系统订单状态
         */
        OrderCondiInDto indto = new OrderCondiInDto();


        int ndate = ORDERDATA_INTERVAL_NDAY;

        Calendar cal = Calendar.getInstance();
        Date edate = new Date();
        cal.setTime(edate);
        cal.add(Calendar.DATE,-1 * ndate);
        String endDate = DateUtil.date2String(edate,"yyyy-MM-dd");
        String startDate = DateUtil.date2String(cal.getTime(),"yyyy-MM-dd");

        indto.setWebsiteid(3);
        indto.setDhStartDt(startDate);
        indto.setDhEndDt(endDate);
        List<OrderDto> orderDtoList = orderDao.findOrderNulPay(indto);
        if (orderDtoList != null){
            for (OrderDto orderDto : orderDtoList ){
                boolean hav = false;
                //本次同步产生的订单不需要进行状态更新
                for (String errOrder : errOrderList){
                    if (errOrder.equals(orderDto.getWebsiteorderno())){
                        hav = true;
                        break;
                    }
                }
                if (!hav) {
                    try {
                        orderDataMakeService.updOrderInfo(service, orderDto);
                    } catch (Exception e) {
                        System.out.println("销售订单（" + orderDto.getWebsiteorderno() + "）更新支付状态失败");
                        System.out.println(e.toString());
                    }
                }
            }
        }
        System.out.println("定时订单支付状态更新结束#################");
    }

    public void testTransactional(){

        try{
            for (int i =1000; i<1007;i++) {
                String err ="";
                try {
                    orderDataMakeService.maked(i);
                }catch (Exception e){
                    err = e.toString();
                }
                if (!"".equals(err)){

                    generateErrorService.adde(i,err);
                }

            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        orderDataMakeService.maked(3001);
        orderDataMakeService.maked(1003);

    }
}
