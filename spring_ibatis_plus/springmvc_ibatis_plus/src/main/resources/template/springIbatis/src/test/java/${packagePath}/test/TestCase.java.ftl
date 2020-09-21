package ${packageName}.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.service.${className}Service;

/**
 * Junit 单元测试
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@Transactional
public class TestCase
{
    
    @Autowired
    ${className}Service ${className?uncap_first}Service;
    
    @Test
    public void test()
    {
        ${className?uncap_first}Service.queryAll();
    }
    
}
