package com.pluralsight.conference;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@Configuration
public class ConferenceConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver getViewResolver() {
        System.out.println("ConferenceConfig - getViewResolver() called..");
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        bean.setOrder(0);
        bean.setContentType("text/html;charset=UTF-8");
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("ConferenceConfig - addResourceHandlers() invoked.");
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("/WEB-INF/pdf/");
    }

    @Bean
    public LocaleResolver localeResolver() {
        System.out.println("ConferenceConfig - localeResolver() invoked");
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        System.out.println("ConferenceConfig - localeChangeInterceptor() invoked");
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("ConferenceConfig - addInterceptors() invoked");
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        System.out.println("ConferenceConfig - stringHttpMessageConverter() invoked");
        //return new StringHttpMessageConverter(Charset.forName("UTF-8"));
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        //messageConverter.setSupportedMediaTypes(Arrays.asList());
        return messageConverter;
    }
}
