package com.taskDistributor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.taskDistributor.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setOrder(1);
        bean.setSuffix(".jsp");
        bean.setPrefix("/WEB-INF/views/");
        return bean;
    }
//    @Bean
//    public ViewResolver resourceBundleViewResolver() {
//        ResourceBundleViewResolver bean = new ResourceBundleViewResolver();
//        bean.setBasename("views");
//        return bean;
//    }


}
