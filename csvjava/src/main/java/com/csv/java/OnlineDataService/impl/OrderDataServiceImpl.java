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

        final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
        service = remoteServiceFactory.getOrderRemoteService();

        //获取新的订单添加到库
        SysCodeDto sysCodeDto = new SysCodeDto();
        sysCodeDto.setSysTypeCd(3);
        sysCodeDto.setSysCd("1");
        SysCodeDto forIncrementId = sysCodeDao.findSysCodeBySysCd(sysCodeDto);
        Filter filter = new Filter();
        filter.getItems().add(new FilterItem("increment_id", ">", forIncrementId.getSysNm()+""));

        try{
            List<Order> newOrderList = service.list(filter);
            for (Order newOrder : newOrderList) {
                String err ="";
                //数据同步
                try {
                    orderDataMakeService.makeOrderInfo(service, newOrder.getOrderNumber(), 1);
                }catch (Exception e){
                    err = toString();
                }
                if (!"".equals(err)){
                    /*数据失败则添加此数据到网站失败订单记录表中
                    * 如果添加到记录表失败，则后续所有数据同步处理不进行，以防记录表单没有记录上，在下回数据同步时，
                    * 此条订单不会被同步。这样，字典表配置的id是能够记录到失败记录情况下的，最后一次的订单号，那么，
                    * 在下次数据同步时，仍可以根据字典表配置的网站订单id开始同步*/
                    genenateErrorService.addGenenateError(newOrder.getOrderNumber(),err);
                }
            }
        }catch (ServiceException e){
            System.out.println(e.toString());
        }

        //之前同步失败的网站订单在每次继续检查同步
        List<GenenateErrorDto> genenateErrorDtoList = genenateErrorDao.findErrOrderNo(3);
        for (GenenateErrorDto genenateErrorDto : genenateErrorDtoList) {
            orderDataMakeService.makeOrderInfo(service,genenateErrorDto.getWebsiteOrderNo(),2);
        }
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
