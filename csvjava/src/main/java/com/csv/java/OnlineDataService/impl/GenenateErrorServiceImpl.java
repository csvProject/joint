package com.csv.java.OnlineDataService.impl;





import com.csv.java.OnlineDataService.GenenateErrorService;
import com.csv.java.dao.GenenateErrorDao;
import com.csv.java.entity.GenenateErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "genenateServiceImpl")
public class GenenateErrorServiceImpl implements GenenateErrorService {

    @Autowired
    private GenenateErrorDao genenateErrorDao;

    //子事务test
    @Transactional
    public void adde(int orderNumber,String err){
        //添加此数据到同步失败的网站订单记录表中

        if (orderNumber == 1005) {
            GenenateErrorDto genenateErrorDto2 =new GenenateErrorDto();
            genenateErrorDto2.setWebsiteId(3);
            genenateErrorDto2.setWebsiteOrderNo("X2001");
            genenateErrorDao.delGenenateError(genenateErrorDto2);
            String a = "aa";
            int b = Integer.parseInt(a);
        }
        GenenateErrorDto genenateErrorDto =new GenenateErrorDto();
        genenateErrorDto.setWebsiteId(3);
        genenateErrorDto.setWebsiteOrderNo("E"+orderNumber);
        genenateErrorDto.setErrorInfo(err);
        genenateErrorDao.insertGenenateError(genenateErrorDto);
    }

    //起始添加此数据到同步失败的网站订单记录表中
    @Transactional
    public void addGenenateError(String orderNumber,String err){
        //添加此数据到同步失败的网站订单记录表中
        GenenateErrorDto genenateErrorDto =new GenenateErrorDto();
        genenateErrorDto.setWebsiteId(3);
        genenateErrorDto.setWebsiteOrderNo(orderNumber);
        genenateErrorDto.setErrorInfo(err);
        genenateErrorDao.delGenenateError(genenateErrorDto);
        genenateErrorDao.insertGenenateError(genenateErrorDto);
    }


}
