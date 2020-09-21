package com.baomidou.mybatisplus.test.h2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.baomidou.mybatisplus.test.h2.entity.persistent.H2User;
import com.baomidou.mybatisplus.test.h2.service.IH2UserService;

/**
 * 测试XML配置
 * @author nieqiurong 2018/8/14 13:30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:h2/spring-test-xml-h2.xml"})
public class TestXmlConfig {

    @Autowired
    protected IH2UserService userService;

    @Test
    public void test() {
        H2User user = userService.getById(101L);
        Assert.assertNotNull(user);
    }

}
