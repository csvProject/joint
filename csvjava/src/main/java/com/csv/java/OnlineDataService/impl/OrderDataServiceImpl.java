package com.csv.java.OnlineDataService.impl;





import com.csv.java.OnlineDataService.GenenateErrorService;
import com.csv.java.OnlineDataService.OrderDataMakeService;
import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.dao.GenenateErrorDao;
import com.csv.java.dao.SysCodeDao;
import com.csv.java.entity.GenenateErrorDto;
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
import java.util.List;

@Service(value = "orderDataService")
public class OrderDataServiceImpl implements OrderDataService {

    @Autowired
    private GenenateErrorService genenateErrorService;

    @Autowired
    private OrderDataMakeService orderDataMakeService;

    @Autowired
    private SysCodeDao sysCodeDao;

    @Autowired
    private GenenateErrorDao genenateErrorDao;

    private OrderRemoteService service;

    //获取新的订单及更新已有未付款订单的状态
    public void GenenateOrderDataFromMagento(){

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
            List<Order> newOrderList = service.list(filter);
            for (Order newOrder : newOrderList) {
                String err ="";

                try {
                    //销售订单同步到订单系统
                    orderDataMakeService.makeOrderInfo(service, newOrder.getOrderNumber(), 1);
                }catch (Exception e){
                    err =e.toString();
                }
                if (!"".equals(err)){
                    System.out.println("订单（"+ newOrder.getOrderNumber() +"）同步失败，开始登记到同步错误记录表");
                    //同步错误的销售订单记录到同步错误记录表中
                    genenateErrorService.addGenenateError(newOrder.getOrderNumber(),err);
                    System.out.println("成功登记到同步错误记录表");
                    errOrderList.add(newOrder.getOrderNumber());
                }
            }
        }catch (ServiceException e){
            System.out.println(e.toString());
        }

        //之前同步失败的同步错误记录表中订单在每次继续检查同步
        List<GenenateErrorDto> genenateErrorDtoList = genenateErrorDao.findErrOrderNo(3);
        for (GenenateErrorDto genenateErrorDto : genenateErrorDtoList) {
            boolean hav = false;
            //本次处理产生失败订单不需要进行再同步
            for (String errOrder : errOrderList){
                if (errOrder.equals(genenateErrorDto.getWebsiteOrderNo())){
                    hav = true;
                    break;
                }
            }
            if (!hav) {
                try {
                    orderDataMakeService.makeOrderInfo(service, genenateErrorDto.getWebsiteOrderNo(), 2);
                } catch (Exception e) {
                    System.out.println("同步错误记录表订单（"+ genenateErrorDto.getWebsiteOrderNo() +"）再同步失败");
                    System.out.println(e.toString());
                }
            }
        }
        System.out.println("定时数据同步结束++++++++++++++++++++");

        System.out.println("定时订单状态更新开始#################");
        /* 订单状态更新
        1.获取订单系统中所有未付款的订单
        2.查询对应销售订单，更新订单系统订单状态
         */

        System.out.println("定时订单状态更新结束#################");
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

                    genenateErrorService.adde(i,err);
                }

            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        orderDataMakeService.maked(3001);
        orderDataMakeService.maked(1003);

    }
}
