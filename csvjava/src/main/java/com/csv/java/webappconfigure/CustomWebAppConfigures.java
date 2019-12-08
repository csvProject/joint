package com.csv.java.webappconfigure;

import com.csv.java.webappconfigure.interceptor.CommonInterceptor;
import com.csv.java.webappconfigure.interceptor.EwiderappInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/*.js","/*.css","/*.ico","/*.jpg",
                        "/*.txt","/index.html", "/assets/**");
        /*registry.addInterceptor(new EwiderappInterceptor()).addPathPatterns("/ewiderapp/**")
                .excludePathPatterns("/*.js","/*.css","/*.ico","/*.jpg",
                        "/*.txt","/index.html", "/assets/**");*/
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(3600);
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        *//*WebMvcConfigurer.super.addResourceHandlers(registry);*//*
    }*/
}
