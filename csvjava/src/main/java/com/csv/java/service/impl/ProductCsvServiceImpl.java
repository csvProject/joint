package com.csv.java.service.impl;





import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.dao.ProductDao;
import com.csv.java.entity.*;
import com.csv.java.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "productCsvService")
public class ProductCsvServiceImpl implements ProductCsvService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CsvTemplateRuleDao csvTemplateRuleDao;

    public List<ProductDto> findProductByCondi(ProductCondiInDto indto){
        return productDao.findProductByCondi(indto);
    }

    public ProductDto findProductById(int productId){
        return productDao.findProductById(productId);
    }

    public String exportProductCsv(CsvExportInDto csvExportInDto){
        String ret="";

        List <ProductGroupOutDto> productGroupOutDtoList = new ArrayList();

        //根据产品类型与供应商分组
        for ( ProductDto productDto:csvExportInDto.getProductDtoList()) {

            Boolean groupHave= false;
            for (ProductGroupOutDto productGroupOutDto : productGroupOutDtoList){

                //分组存在则把产品添加到分组
                if (productDto.getPtypeId() == productGroupOutDto.getPtypeId() &&
                        productDto.getsId() == productGroupOutDto.getsId()){
                    groupHave = true;

                    /*//账号
                    productGroupOutDto.setPfaccountId(csvExportInDto.getPfaccountId());
                    //平台
                    productGroupOutDto.setPlatformId(csvExportInDto.getPlatformId());
                    //产品类型
                    productGroupOutDto.setPtypeId(productDto.getPtypeId());
                    //供应商
                    productGroupOutDto.setsId(productDto.getsId());*/

                    //每个分组下添加产品信息
                    if (productGroupOutDto.getProductDtoList()== null){
                        List <ProductDto> tmpPdList = new ArrayList();
                        tmpPdList.add((ProductDto)productDto.clone());
                        productGroupOutDto.setProductDtoList(tmpPdList);
                    }else{
                        productGroupOutDto.getProductDtoList().add((ProductDto)productDto.clone());
                    }
                }
            }

            //没有分组则新建组
            if (!groupHave){
                //分组信息
                ProductGroupOutDto tmpPdGrp = new ProductGroupOutDto();
                //账号
                tmpPdGrp.setPfaccountId(csvExportInDto.getPfaccountId());
                //平台
                tmpPdGrp.setPlatformId(csvExportInDto.getPlatformId());
                //产品类型
                tmpPdGrp.setPtypeId(productDto.getPtypeId());
                //供应商
                tmpPdGrp.setsId(productDto.getsId());

                //每个分组下添加产品信息
                List <ProductDto> tmpPdList = new ArrayList();
                tmpPdList.add((ProductDto)productDto.clone());
                tmpPdGrp.setProductDtoList(tmpPdList);

                //添加到分组集合内
                productGroupOutDtoList.add(tmpPdGrp);
            }
        }

        //设定每个分组对应的csvsql
        for (ProductGroupOutDto productGroupOutDto : productGroupOutDtoList) {
            String fourKey = String.valueOf(productGroupOutDto.getPlatformId()) +
                    String.valueOf(productGroupOutDto.getPfaccountId()) +
                    String.valueOf(productGroupOutDto.getPtypeId()) +
                    String.valueOf(productGroupOutDto.getsId());
            CsvTemplateRuleDto csvTemplateRuleDto = csvTemplateRuleDao.findCsvsqlByFourKey(fourKey);
            productGroupOutDto.setCsvSql(csvTemplateRuleDto.getCsvSql());
        }

        //生成csv
        for (ProductGroupOutDto productGroupOutDto : productGroupOutDtoList) {
            for ( ProductDto productDto:productGroupOutDto.getProductDtoList()) {

            }
        }


        return ret;

    }

}
