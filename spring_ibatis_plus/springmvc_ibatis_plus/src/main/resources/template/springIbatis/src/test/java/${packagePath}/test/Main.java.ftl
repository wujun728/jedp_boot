package ${packageName}.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import ${packageName}.service.${className}Service;

/**
 * 
 * Main
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Main
{
    /**
     * Main在单独的application的main函数中初始化spring
     * 
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        // 可修改service代码去测试异常情况下事务是否起作用
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(true);
        context.load("classpath:applicationContext.xml");
        context.refresh();
        ${className}Service ${className?uncap_first}Service = context.getBean(${className}Service.class);
        System.out.println(${className?uncap_first}Service.queryAll());
    }
}
