package com.csv.java.webappconfigure;

import com.csv.java.webappconfigure.interceptor.CommonInterceptor;
import com.csv.java.webappconfigure.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.csv.java.config.ConstantConfig.*;

/**
 * wkm
 * https://www.cnblogs.com/hfultrastrong/p/8583874.html
 */
@Configuration
public class CustomWebAppConfigures implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成拦截链
        // addPathPatterns 添加拦截规则
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**");
    }

 /*   @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(Origins)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(false).maxAge(3600);
    }*/
}
