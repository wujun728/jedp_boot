package com.springmvc.config;

import com.springmvc.interceptor.DemoInterceptor2;
import com.springmvc.messageConverter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by zsq on 16/12/20.
 * springmvc 配置
 * @EnableWebMvc 开启webmvc 配置
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.springmvc")
public class WebMVCConfig extends WebMvcConfigurerAdapter{

    /**
     * 其中/WEB-INF/classes/views 目录为运行时 把视图文件编译在该目录下,不是我们的开发目录
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 配置静态资源的访问
     * addResourceLocations 指文件放置的目录
     * addResourceHandler 指对外暴露的访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 创建自定义拦截器对象并返回bean
     * @return
     */
    @Bean
    public DemoInterceptor2 demoInterceptor2() {
        return new DemoInterceptor2();
    }

    /**
     * 重写添加拦截器方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor2());
    }

    /**
     * 定义ViewController 相当于定义一个统一的页面跳转
     * 只要请求的是addViewController()中定义的urlPath
     * 就会返回到setViewName()中定义的view视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/toConverter").setViewName("/convert");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");
    }

    /**
     * 默认情况下 springmvc  路径参数中如果带"."的话,"."后面的值会被忽略
     * eg:http://localhost:8080/anno/pathVar/xx.yy,此时"."后面yy会被忽略.
     * 重写configurePathMatch()方法可以不忽略"."后面的参数值.
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 配置文件上传接口MultipartResolver
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(1000000);
        return commonsMultipartResolver;
    }

    /**
     * 返回自定义的 HttpMessageConverter 即 MessageConverter
     * @return
     */
    @Bean
    public MessageConverter converter() {
        return new MessageConverter();
    }

    /**
     * 在springmvc 中注册 HttpMessageConverter 有两种方法
     * （1）configureMessageConverters：重写configureMessageConverters方法,这样会覆盖掉springmvc 默认注册的多个HttpMessageConverter。
     * （2）extendMessageConverters：重写extendMessageConverters方法，仅添加一个自定义的HttpMessageConverter，
     *     不会覆盖默认的注册的HttpMessageConverter。
     *
     * 重写extendMessageConverters() 方法, 仅添加一个自定义的 HttpMessageConverter 即 MessageConverter
     * 这样不会覆盖默认注册的 HttpMessageConverter
     * @param converters
     */
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }
}
