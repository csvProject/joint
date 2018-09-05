package com.csv.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    public void initialization() {
        Origins = _environment.getProperty("cors.mappings.origins")==null?"*".split(","):_environment.getProperty("cors.mappings.origins").split(",");
        FIELD_MATCHING_TABLE = _environment.getProperty("field.matching.table");
        CSV_FILE_TEMP_PATH = _environment.getProperty("csv.file.temp.path");
        CSV_ZIP_FILE_TEMP_PATH = _environment.getProperty("csv.zip.file.temp.path");
        DELETE_ZIP_FILE_DAY = Integer.getInteger(_environment.getProperty("delete.zip.file.day"));
    }
}
