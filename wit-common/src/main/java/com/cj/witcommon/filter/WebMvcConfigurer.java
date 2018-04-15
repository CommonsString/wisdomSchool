package com.cj.witcommon.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * description:
 * ===>mvc配置属性类
 *
 * @author duyuxiang Created on 2017-12-28 10:06
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    //自定义系统主页
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("redirect:/static/main.html");
        registry.addViewController("/").setViewName("redirect:/static/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

}
