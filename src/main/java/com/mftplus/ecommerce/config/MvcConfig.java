package com.mftplus.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/search").setViewName("index");
        registry.addViewController("/product/**").setViewName("index");
        registry.addViewController("/cart").setViewName("index");
        registry.addViewController("/payment").setViewName("index");
        registry.addViewController("/admin/**").setViewName("index");
        registry.addViewController("/login").setViewName("index");
        registry.addViewController("/register").setViewName("index");
        registry.addViewController("/profile/**").setViewName("index");
        registry.addViewController("/payment/**").setViewName("index");

    }

}
