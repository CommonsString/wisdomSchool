package com.cj.witcommon.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by zsl on 2017/9/3.
 */
@Configuration
public class ConfigurationFilter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean UserFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserFilter());//添加过滤器
        registration.addUrlPatterns("/user/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("name", "value");//添加默认参数
        registration.setName("UserFilter");//设置优先级
        registration.setOrder(Integer.MAX_VALUE-1);//设置优先级
        return registration;
    }

    @Bean
    public FilterRegistrationBean AdminFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AdminFilter());//添加过滤器
        registration.addUrlPatterns("/api/v1/admin1/*");//设置过滤路径，/*所有路径
//        registration.addInitParameter("name", "value");//添加默认参数
        registration.setName("AdminFilter");//设置优先级
        registration.setOrder(Integer.MAX_VALUE-2);//设置优先级
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean CookleFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new CookieFilter());//添加过滤器
//        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.addInitParameter("name", "value");//添加默认参数
//        registration.setName("CookieFilter");//设置优先级
//        registration.setOrder(2);//设置优先级
//        return registration;
//    }

    @Bean
    public FilterRegistrationBean OriginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new OriginFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.addInitParameter("name", "value");//添加默认参数
        registration.setName("OriginFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory multipartConfig = new MultipartConfigFactory();
        //单次上传文件总数最大为15M
        multipartConfig.setMaxFileSize("15MB");
        //每次http请求大小最大为15M
        multipartConfig.setMaxRequestSize("15MB");
        return multipartConfig.createMultipartConfig();
    }








}