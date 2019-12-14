package com.ewiderbuy.produce.service.impl;

import com.ewiderbuy.produce.common.tool.CSVUtils;
import com.ewiderbuy.produce.common.tool.DeleteFileUtil;
import com.ewiderbuy.produce.common.tool.ServiceUtil;
import com.ewiderbuy.produce.config.ConstantConfig;
import com.ewiderbuy.produce.dao.CsvCustomFieldDao;
import com.ewiderbuy.produce.dao.CsvTemplateRuleDao;
import com.ewiderbuy.produce.dao.CustomDao;
import com.ewiderbuy.produce.dao.ProductDao;
import com.ewiderbuy.produce.entity.*;
import com.ewiderbuy.produce.service.ProductCsvService;
import com.ewiderbuy.produce.common.tool.CSVUtils;
import com.ewiderbuy.produce.common.tool.DeleteFileUtil;
import com.ewiderbuy.produce.common.tool.ServiceUtil;
import com.ewiderbuy.produce.common.tool.StringFormatForSQL;
import com.ewiderbuy.produce.config.ConstantConfig;
import com.ewiderbuy.produce.config.ScheduledComponent;
import com.ewiderbuy.produce.dao.CsvTemplateRuleDao;
import com.ewiderbuy.produce.entity.*;
import com.ewiderbuy.produce.service.ProductCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ewiderbuy.produce.common.tool.StringFormatForSQL.csvExportSql;
import static com.ewiderbuy.produce.common.tool.UUIDUtil.getUUID;
import static com.ewiderbuy.produce.common.tool.ZipFileUtils.zipPath;
import static com.ewiderbuy.produce.config.ConstantConfig.CSV_FILE_TEMP_PATH;
import static com.ewiderbuy.produce.config.ConstantConfig.CSV_ZIP_FILE_TEMP_PATH;
import static com.ewiderbuy.produce.config.ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT;
import static com.ewiderbuy.produce.config.ScheduledComponent.ZIP_FILES;

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

    public Map<String, Object> findProductByCondiNoPage(ProductCondiInDto indto){
        List<ProductDto> productDtoList = new ArrayList();

        productDtoList = productDao.findProductByCondi(indto);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", productDtoList);
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
        //能导出的数据合计
        int exportCount = 0;
        Boolean isOutLimit = false;
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

        //String zipFileName = getUUID();
        String tmpfiename = csvExportInDto.getPlatformNm()+"_"+csvExportInDto.getPfaccountNm();

        SimpleDateFormat fmtzip = new SimpleDateFormat("yyyyMMddHHmmssSSS"); //精确到毫秒
        String suffixzip = fmtzip.format(new Date());
        String zipFileName = tmpfiename + "_" + suffixzip;

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
                sql = StringFormatForSQL.csvExportSql(productGroupOutDto.getCsvSql(),map);
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
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); //精确到毫秒
                    String suffix = fmt.format(new Date());
                    String fileName = tmpfiename + "_" + suffix + ".CSV";

                    productGroupOutDto.setCsvFileName(fileName);
                    if(0 == productGroupOutDto.getHeadShow()){
                        CSVUtils.createCSV(heads,dataList,fileName, ConstantConfig.CSV_FILE_TEMP_PATH, ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT);
                    }else{
                        CSVUtils.createCSV(null,dataList,fileName, ConstantConfig.CSV_FILE_TEMP_PATH, ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT);
                    }
                    filePaths.add(ConstantConfig.CSV_FILE_TEMP_PATH + fileName);
                    if (!isOutLimit &&  dataList.size() > ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT) {
                        isOutLimit = true;
                    }
                }
            }
        }
        String noCsvTempFileName = "";

        try {
            //存在无模板数据导出则生成一份无模板产品列表
            if ((noCsvTempList != null && noCsvTempList.size()>0) || isOutLimit) {
                noCsvTempFileName = noCsvFile(zipFileName, noCsvTempList,isOutLimit, ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT);
                filePaths.add(ConstantConfig.CSV_FILE_TEMP_PATH + noCsvTempFileName);
            }
            zipPath(ConstantConfig.CSV_ZIP_FILE_TEMP_PATH + zipFileName+".zip",filePaths);
            FileDto zipFile= new FileDto();
            zipFile.setCreateTime(new Date().getTime());
            zipFile.setFileName(ConstantConfig.CSV_ZIP_FILE_TEMP_PATH + zipFileName+".zip");
            ScheduledComponent.ZIP_FILES.add(zipFile);
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
        ret.put("isOutLimit",isOutLimit); //导出是否有超出限制数的
        ret.put("outLimit", ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT); //导出限制数
        return ret;
    }

    /* 生成无模板csv文件 */
    private String noCsvFile(String zipFileName, List<ProductDto> noCsvTempList,
                             Boolean isOutLimit,int limitCount){
        List<List<Object>> dataList = new ArrayList<List<Object>>();

        if (isOutLimit){
            List<Object> rowList3 = new ArrayList<>();
            rowList3.add("原因：可导出数据超出导出限制条数（"+limitCount + "），部分数据没有被导出");
            dataList.add(rowList3);
        }
        if (noCsvTempList.size() > 0 ) {
            //数据
            List<Object> rowList1 = new ArrayList<>();
            rowList1.add("原因：未定义模板");
            dataList.add(rowList1);

            String sku = "sku:";
            for (ProductDto productDtos : noCsvTempList) {
                String tmp = productDtos.getSku() == null ? "" : productDtos.getSku() + "";
                sku = sku + tmp + ",";

            }
            List<Object> rowList2 = new ArrayList<>();
            rowList2.add(sku);
            dataList.add(rowList2);


        }
        String fileName = "ERROR_"+zipFileName+".CSV";
        CSVUtils.createCSV(null,dataList,fileName, ConstantConfig.CSV_FILE_TEMP_PATH, ConstantConfig.EXPORT_CSV_PRODUCT_LIMITCOUNT);
        return  fileName;
    }
}
