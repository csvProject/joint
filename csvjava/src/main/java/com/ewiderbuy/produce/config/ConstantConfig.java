package com.ewiderbuy.produce.config;

import com.ewiderbuy.produce.common.tool.DeleteFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static com.ewiderbuy.produce.common.tool.DeleteFileUtil.deleteFile;

@Configuration
public class ConstantConfig {
    @Autowired
    private Environment _environment;

    /*跨域访问配置*/
    public static String[] Origins;

    /*字段表*/
    public static String FIELD_MATCHING_TABLE;

    /*CSV文件临时存储路径*/
    public static String CSV_FILE_TEMP_PATH;

    /*CSV压缩文件临时存储路径*/
    public static String CSV_ZIP_FILE_TEMP_PATH;

    /*删除CSV压缩文件时间*/
    public static Integer DELETE_ZIP_FILE_DAY;

    /*导出数据行数限制*/
    public static Integer EXPORT_CSV_PRODUCT_LIMITCOUNT;

    /*magento 网址*/
    public static String MAGENTO_HOST;

    //订单支付状态时所查询订单范围（n天内）
    public static int ORDERDATA_INTERVAL_NDAY;

    public static String SPRING_PROFILES_ACTIVE;

    public static String CSCS_CONFIG_TOKENEXT;

    @PostConstruct
    public void initialization() {
        Origins = _environment.getProperty("cors.mappings.origins")==null?"*".split(","):_environment.getProperty("cors.mappings.origins").split(",");
        FIELD_MATCHING_TABLE = _environment.getProperty("field.matching.table");
        CSV_FILE_TEMP_PATH = _environment.getProperty("csv.file.temp.path");
        CSV_ZIP_FILE_TEMP_PATH = _environment.getProperty("csv.zip.file.temp.path");
        DELETE_ZIP_FILE_DAY = Integer.parseInt(_environment.getProperty("delete.zip.file.day"));
        MAGENTO_HOST = _environment.getProperty("magento.host");

        EXPORT_CSV_PRODUCT_LIMITCOUNT = Integer.parseInt(_environment.getProperty("export.csv.product.limitcount"));
        ORDERDATA_INTERVAL_NDAY = Integer.parseInt(_environment.getProperty("orderdata.interval.nday"));

        SPRING_PROFILES_ACTIVE  = _environment.getProperty("spring.profiles.active");

        CSCS_CONFIG_TOKENEXT = _environment.getProperty("cscs.config.tokenExt");
        initDeleteZipFile();
    }

    private void initDeleteZipFile(){
        File csvFile = new File(CSV_ZIP_FILE_TEMP_PATH);
        csvFile.mkdirs();
        File[] files = csvFile.listFiles();
        for (File file:files) {
            DeleteFileUtil.deleteFile(CSV_ZIP_FILE_TEMP_PATH + file.getName());
        }
    }
}
