package com.springmvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by zsq on 16/12/20.
 * web 配置
 * WebApplicationInitializer 是spring 提供用来配置servlet 3.0的接口 从而实现替代web.xml
 * 实现此接口会自动被 SpringServletContainerInitializer (用来启动servlet 3.0的容器) 获取到
 *
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMVCConfig.class);
        context.setServletContext(servletContext);

        //注册springmvc 的 dispatcherServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true); //开启异步支持
    }
}
