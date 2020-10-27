package com.github.zhangkaitao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * User: zhangkaitao
 * Date: 14-7-31
 * Time: ����8:00
 * Version: 1.0
 */
@ActiveProfiles("production")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-yml.xml")
public class YamlTest {
    @Autowired
    private ApplicationContext ctx;

    @Autowired
    @Qualifier("yamlMap")
    private Map<String, Object> yamlMap;

    @Autowired
    @Qualifier("yamlProperties")
    private Properties yamlProperties;
    @Test
    public void testYmlMap() {
        //Map������ֱ��ע��@Autowired Map��
        //��ο� Map����ע�루http://jinnianshilongnian.iteye.com/blog/1989379��
        System.out.println(this.yamlMap);
        Map<String, Object> yamlMap = ctx.getBean("yamlMap", Map.class);
        //��Ҫsnakeyaml �ù����Ǵ�spring-boot�����
        Map<String, Object> env = (Map<String, Object>) yamlMap.get("env");
        Map<String, Object> one = (Map<String, Object>) env.get("one");
        Assert.assertEquals("zhangsan", one.get("name"));

        List<Map<String, Object>> two = (List) env.get("two");
        Assert.assertEquals(1, two.get(0).get("a"));
        Assert.assertEquals("3", two.get(1).get("c"));

        Assert.assertEquals(null, env.get("three"));


        //Properties
        Assert.assertEquals("zhangsan", yamlProperties.getProperty("env.one.name"));
        //getProperty������ص�����ʱ��String���򷵻�null
        Assert.assertEquals(1, yamlProperties.get("env.two[0].a"));
        Assert.assertEquals("3", yamlProperties.getProperty("env.two[1].c"));
        Assert.assertEquals("", yamlProperties.getProperty("env.three"));


        //spring.profiles
        //http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config-yaml
    }

}
