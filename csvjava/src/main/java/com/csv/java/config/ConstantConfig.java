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

    @PostConstruct
    public void initialization() {
        Origins = _environment.getProperty("cors.mappings.origins")==null?"*".split(","):_environment.getProperty("cors.mappings.origins").split(",");
        FIELD_MATCHING_TABLE = _environment.getProperty("field.matching.table");
    }
}
