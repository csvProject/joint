package com.csv.java.service.impl;





import com.csv.java.service.GenerateErrorService;
import com.csv.java.dao.GenerateErrorDao;
import com.csv.java.entity.GenerateErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "generateServiceImpl")
public class GenerateErrorServiceImpl implements GenerateErrorService {

    @Autowired
    private GenerateErrorDao generateErrorDao;

    //子事务test
    @Transactional
    public void adde(int orderNumber,String err){
        //添加此数据到同步失败的网站订单记录表中

        if (orderNumber == 1005) {
            GenerateErrorDto generateErrorDto2 =new GenerateErrorDto();
            generateErrorDto2.setWebsiteId(3);
            generateErrorDto2.setWebsiteOrderNo("X2001");
            generateErrorDao.delGenerateError(generateErrorDto2);
            String a = "aa";
            int b = Integer.parseInt(a);
        }
        GenerateErrorDto generateErrorDto =new GenerateErrorDto();
        generateErrorDto.setWebsiteId(3);
        generateErrorDto.setWebsiteOrderNo("E"+orderNumber);
        generateErrorDto.setErrorInfo(err);
        generateErrorDao.insertGenerateError(generateErrorDto);
    }

    //起始添加此数据到同步失败的网站订单记录表中
    @Transactional
    public void addGenerateError(String orderNumber,String err){
        //添加此数据到同步失败的网站订单记录表中
        GenerateErrorDto generateErrorDto =new GenerateErrorDto();
        generateErrorDto.setWebsiteId(3);
        generateErrorDto.setWebsiteOrderNo(orderNumber);
        generateErrorDto.setErrorInfo(err);
        generateErrorDao.delGenerateError(generateErrorDto);
        generateErrorDao.insertGenerateError(generateErrorDto);
    }


}
