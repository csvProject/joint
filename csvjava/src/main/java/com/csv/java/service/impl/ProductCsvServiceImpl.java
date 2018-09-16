package com.csv.java.service.impl;

import com.csv.java.common.tool.CSVUtils;
import com.csv.java.common.tool.DeleteFileUtil;
import com.csv.java.common.tool.ServiceUtil;
import com.csv.java.dao.CsvCustomFieldDao;
import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.dao.CustomDao;
import com.csv.java.dao.ProductDao;
import com.csv.java.entity.*;
import com.csv.java.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.csv.java.common.tool.StringFormatForSQL.csvExportSql;
import static com.csv.java.common.tool.UUIDUtil.getUUID;
import static com.csv.java.common.tool.ZipFileUtils.zipPath;
import static com.csv.java.config.ConstantConfig.CSV_FILE_TEMP_PATH;
import static com.csv.java.config.ConstantConfig.CSV_ZIP_FILE_TEMP_PATH;
import static com.csv.java.config.ScheduledComponent.ZIP_FILES;

@Service(value = "productCsvService")
public class ProductCsvServiceImpl implements ProductCsvService {

    @Resource
    private ProductDao productDao;

    @Resource
    private CsvTemplateRuleDao csvTemplateRuleDao;

    @Resource
    private CustomDao customDao;

    @Resource
    private CsvCustomFieldDao csvCustomFieldDao;

    public Map<String, Object> findProductByCondi(ProductCondiInDto indto){
        List<ProductDto> productDtoList = new ArrayList();
        Object hList = new ArrayList<Object>();

        productDtoList = productDao.findProductByCondi(indto);

        ServiceUtil serviceUtil = new ServiceUtil();
        int psize = indto.getPageSize();
        int pstart = indto.getPageStart();
        //根据条件查询所有数据条数和当页所有数据
        hList = serviceUtil.findListPage(productDtoList,psize,pstart);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", hList);
        map.put("count", productDtoList.size());
        return map;
    }

    public ProductDto findProductById(int productId){
        return productDao.findProductById(productId);
    }

    @Transactional
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
                productGroupOutDto.setCsvtempId(csvTemplateRuleDto.getCsvtempId());
                productGroupOutDto.setHeadShow(csvTemplateRuleDto.getHeadShow());
            }
        }

        List noCsvTempList = new ArrayList(); //没有模板商品集合

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); //精确到毫秒
        String suffix = fmt.format(new Date());

        //String zipFileName = getUUID();
        String tmpfiename = csvExportInDto.getPlatformNm()+"_"+csvExportInDto.getPfaccountNm()+"_"+suffix;
        String zipFileName = tmpfiename;
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
                String sql = "";
                /* 自定义公式 */
                Map map = new HashMap();
                List<CsvCustomFieldDto> tempList =  csvCustomFieldDao.findCsvCustomField(productGroupOutDto.getCsvtempId());
                for (CsvCustomFieldDto s: tempList) {
                    if(s.getCfieldValue() == null){
                        s.setCfieldValue("");
                    }
                    map.put("t_csvcustom_field."+s.getCsvCustomFieldId()+"t_csvcustom_field",s.getCfieldValue());
                }
                sql = csvExportSql(productGroupOutDto.getCsvSql(),map);
                sql = sql +"  \n WHERE id in("+ids+")";
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
                            //rowList.add(row.get(k)==null?"":(row.get(k)+"").replaceAll("\"","\\\\"));
                            String value = row.get(k)==null?"":row.get(k)+"";

                            if(value.contains("\"")){ //若发现有双引号  需要将字符串中的一个双引号替换为两个 并且需前后加双引号
                                value = value.replaceAll("\"","\"\"");
                            }
                            rowList.add(value);
                        }
                        dataList.add(rowList);
                    }
                    //String fileName = getUUID()+ ".CSV";
                    String fileName = tmpfiename+ ".CSV";
                    productGroupOutDto.setCsvFileName(fileName);
                    if(0 == productGroupOutDto.getHeadShow()){
                        CSVUtils.createCSV(heads,dataList,fileName,CSV_FILE_TEMP_PATH);
                    }else{
                        CSVUtils.createCSV(null,dataList,fileName,CSV_FILE_TEMP_PATH);
                    }
                    filePaths.add(CSV_FILE_TEMP_PATH + fileName);
                }
            }
        }
        String noCsvTempFileName = "";
        try {
            if (noCsvTempList != null && noCsvTempList.size()>0) {
                noCsvTempFileName = noCsvFile(zipFileName, noCsvTempList);
                filePaths.add(CSV_FILE_TEMP_PATH + noCsvTempFileName);
            }
            zipPath(CSV_ZIP_FILE_TEMP_PATH + zipFileName+".zip",filePaths);
            FileDto zipFile= new FileDto();
            zipFile.setCreateTime(new Date().getTime());
            zipFile.setFileName(CSV_ZIP_FILE_TEMP_PATH + zipFileName+".zip");
            ZIP_FILES.add(zipFile);
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
        heads.add("原因:未定义模板");
        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        String sku = "sku:";
        for(ProductDto productDtos: noCsvTempList ){
            String tmp = productDtos.getSku()==null?"":productDtos.getSku()+"";
            sku = sku  + tmp + ",";

        }
        List<Object> rowList = new ArrayList<>();
        rowList.add(sku);
        dataList.add(rowList);

        String fileName = "ERROR_"+zipFileName+".CSV";
        CSVUtils.createCSV(heads,dataList,fileName,CSV_FILE_TEMP_PATH);
        return  fileName;
    }
}
