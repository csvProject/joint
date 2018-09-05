package com.csv.java.service.impl;

import com.csv.java.common.tool.CSVUtils;
import com.csv.java.common.tool.DeleteFileUtil;
import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.dao.CustomDao;
import com.csv.java.dao.ProductDao;
import com.csv.java.entity.*;
import com.csv.java.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static com.csv.java.common.tool.UUIDUtil.getUUID;
import static com.csv.java.common.tool.ZipFileUtils.zipPath;
import static com.csv.java.config.ConstantConfig.CSV_FILE_TEMP_PATH;
import static com.csv.java.config.ConstantConfig.CSV_ZIP_FILE_TEMP_PATH;
import static com.csv.java.config.ScheduledComponent.ZIP_Files;

@Service(value = "productCsvService")
public class ProductCsvServiceImpl implements ProductCsvService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CsvTemplateRuleDao csvTemplateRuleDao;

    @Resource
    private CustomDao customDao;

    public List<ProductDto> findProductByCondi(ProductCondiInDto indto){
        return productDao.findProductByCondi(indto);
    }

    public ProductDto findProductById(int productId){
        return productDao.findProductById(productId);
    }

    public Map<String,Object> exportProductCsv(CsvExportInDto csvExportInDto){
        Map<String,Object>  ret= new HashMap<>();
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
            if(csvTemplateRuleDto == null){
                productGroupOutDto.setCsvSql("");
            }else{
                productGroupOutDto.setCsvSql(csvTemplateRuleDto.getCsvSql());
            }
        }

        List noCsvTempList = new ArrayList(); //没有模板商品集合

        String zipFileName = getUUID();
        List<String> filePaths = new ArrayList<String>();

        //生成csv
        for (ProductGroupOutDto productGroupOutDto : productGroupOutDtoList) {
            if("".equals(productGroupOutDto.getCsvSql())){
                noCsvTempList.addAll(productGroupOutDto.getProductDtoList());
            }else{
                String ids = "";
                for ( ProductDto productDto:productGroupOutDto.getProductDtoList()) {
                    String s = productDto.getProductId()+"";
                    ids = ids + s + ",";
                }
                if (ids.endsWith(",")) {
                    ids = ids.substring(0,ids.length() - 1) + "";
                }else{
                    ids = ids + "";
                }
                String sql = productGroupOutDto.getCsvSql()+" \n WHERE id in("+ids+")";
                List<LinkedHashMap<String,Object>> csvData = customDao.customSelect(sql);
                if(csvData != null && csvData.size()>0){
                    //csv文件头
                    List heads = new ArrayList<>();
                    System.out.println();
                    for (String k : csvData.get(0).keySet()) {
                        System.out.print(k);
                        heads.add(k);
                    }
                    System.out.println();
                    //数据
                    List<List<Object>> dataList = new ArrayList<List<Object>>();
                    for(Map<String,Object> row: csvData ){
                        List<Object> rowList = new ArrayList<>();
                        for (String k : row.keySet()) {
                            rowList.add(row.get(k)==null?"":(row.get(k)+"").replaceAll("\"","\\\\"));
                        }
                        dataList.add(rowList);
                    }
                    String fileName = getUUID()+ ".CSV";
                    productGroupOutDto.setCsvFileName(fileName);
                    CSVUtils.createCSV(heads,dataList,fileName,CSV_FILE_TEMP_PATH);
                    filePaths.add(CSV_FILE_TEMP_PATH + fileName);
                }
            }
        }
        String noCsvTempFileName = noCsvFile(zipFileName,noCsvTempList);
        try {
            filePaths.add(CSV_FILE_TEMP_PATH + noCsvTempFileName);
            zipPath(CSV_ZIP_FILE_TEMP_PATH + zipFileName+".zip",filePaths);
            FileDto zipFile= new FileDto();
            zipFile.setCreateTime(new Date().getTime());
            zipFile.setFileName(CSV_ZIP_FILE_TEMP_PATH + zipFileName+".zip");
            ZIP_Files.add(zipFile);
            for (String f:filePaths) {
                if(DeleteFileUtil.delete(f)){
                    System.out.println(f + " 删除成功！");
                }else{
                    System.out.println(f + " 删除失败！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("压缩文件："+zipFileName + ".zip 生成失败！");
        }
        ret.put("zipFileName",zipFileName);
        ret.put("noCsvTempFileName",noCsvTempFileName);
        ret.put("noCsvTempList",noCsvTempList); //无模板商品信息
        return ret;
    }

    /* 生成无模板csv文件 */
    private String noCsvFile(String zipFileName,List<ProductDto> noCsvTempList){
        //csv文件头
        List<Object> heads = new ArrayList<>();
        heads.add("图片");
        heads.add("sku");
        heads.add("供应商");
        heads.add("产品类别");
        heads.add("成本价格");
        heads.add("重量");
        heads.add("中文品名");
        heads.add("中文套组");
        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        for(ProductDto productDtos: noCsvTempList ){
            List<Object> rowList = new ArrayList<>();
            rowList.add(productDtos.getPicUrl()==null?"":(productDtos.getPicUrl()+"").replaceAll("\"","\\\\"));
            rowList.add(productDtos.getSku()==null?"":(productDtos.getSku()+"").replaceAll("\"","\\\\"));
            rowList.add(productDtos.getsNm()==null?"":(productDtos.getsNm()+"").replaceAll("\"","\\\\"));
            rowList.add(productDtos.getPtypeNm()==null?"":(productDtos.getPtypeNm()+"").replaceAll("\"","\\\\"));
            rowList.add(productDtos.getBasePrice());
            rowList.add(productDtos.getWeight());
            rowList.add(productDtos.getPmCn()==null?"":(productDtos.getPmCn()+"").replaceAll("\"","\\\\"));
            rowList.add(productDtos.getSetCn()==null?"":(productDtos.getSetCn()+"").replaceAll("\"","\\\\"));
            dataList.add(rowList);
        }
        String fileName = "无模板_"+zipFileName+".CSV";
        CSVUtils.createCSV(heads,dataList,fileName,CSV_FILE_TEMP_PATH);
        return  fileName;
    }
}
