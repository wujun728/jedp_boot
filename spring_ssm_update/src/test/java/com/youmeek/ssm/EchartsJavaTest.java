package com.youmeek.ssm;

import com.github.abel533.echarts.Option;
import com.youmeek.ssm.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Echarts-java测试类
 * @author frank.fang
 * date 16/4/20 16:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 在Maven的pom.xml中设置相对路径
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class EchartsJavaTest {

    private Logger logger = LoggerFactory.getLogger(EchartsJavaTest.class);

    @Autowired
    EventService eventService;

    @Test
    public void test() {
        Option option = eventService.getFireOption();
        logger.info(String.valueOf(option));
    }

}
