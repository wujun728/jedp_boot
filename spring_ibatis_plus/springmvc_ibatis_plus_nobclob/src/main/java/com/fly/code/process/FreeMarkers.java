package com.fly.code.process;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * FreeMarkers
 * 
 * @author 00fly
 * @version [版本号, 2017-4-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FreeMarkers
{
    private static Configuration config;
    static
    {
        config = new Configuration();
        config.setDefaultEncoding("UTF-8");
    }
    
    /**
     * 获取模板填充model解析后的内容
     * 
     * @param template
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String renderTemplate(Template template, Object model)
    {
        try
        {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 获取IbatorConfig模板填充model后的内容
     * 
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String renderIbatorConfigTemplate(Map<String, Object> model)
    {
        try
        {
            config.setClassForTemplateLoading(FreeMarkers.class, "/template/");
            Template template = config.getTemplate("config_ibator.xml.ftl");
            return renderTemplate(template, model);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 获取模板填充model解析后的内容
     * 
     * @param model
     * @param ftlName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String renderTemplate(Map<String, Object> model, String ftlName)
    {
        try
        {
            config.setClassForTemplateLoading(FreeMarkers.class, "/template/springIbatis/");
            Template template = config.getTemplate(ftlName);
            return renderTemplate(template, model);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /***
     * 获取模板填充model解析后的内容
     * 
     * @param templateString
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String renderString(String templateString, Map<String, ?> model)
    {
        try
        {
            StringWriter result = new StringWriter();
            Template t = new Template("name", new StringReader(templateString), config);
            t.process(model, result);
            return result.toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}