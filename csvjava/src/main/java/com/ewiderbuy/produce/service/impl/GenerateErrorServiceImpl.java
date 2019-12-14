package com.ewiderbuy.produce.service.impl;





import com.ewiderbuy.produce.service.GenerateErrorService;
import com.ewiderbuy.produce.dao.GenerateErrorDao;
import com.ewiderbuy.produce.entity.GenerateErrorDto;
import com.ewiderbuy.produce.dao.GenerateErrorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "generateErrorService")
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

    //更新delflag
    public void updDelFlag(int generateErrorId){
        generateErrorDao.updDelFlag(generateErrorId);
    }

    //查询同步失败表数据
    public List<GenerateErrorDto> findErrOrderNo(GenerateErrorDto indto){
        return generateErrorDao.findErrOrderNo(indto);
    }
}
