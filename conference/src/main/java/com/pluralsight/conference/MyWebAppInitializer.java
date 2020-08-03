package com.pluralsight.conference;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer { //implements WebApplicationInitializer {

    /*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        ServletRegistration.Dynamic dispatcher = servletContext
                    .addServlet("dispatcher", new DispatcherServlet(context));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("characterEncodingFilter",
                new CharacterEncodingFilter("UTF-8", true, true));
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        filterRegistration = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter() );
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        //super.onStartup(servletContext);
    }*/
}
