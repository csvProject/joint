package com.csv.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.csv.java.dao")//将项目中对应的dao类的路径加进来就可以了
public class JavaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JavaApplication.class);
    }
}
