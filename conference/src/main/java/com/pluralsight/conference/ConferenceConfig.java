package com.pluralsight.conference;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ConferenceConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver getViewResolver() {
        System.out.println("ConferenceConfig - getViewResolver() called..");
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        bean.setOrder(0);
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("ConferenceConfig - addResourceHandlers() invoked.");
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("/WEB-INF/pdf/");
    }
}
