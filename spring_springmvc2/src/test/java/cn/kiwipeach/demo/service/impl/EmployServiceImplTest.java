package cn.kiwipeach.demo.service.impl;

import cn.kiwipeach.demo.SpringJunitBase;
import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.response.Pageable;
import cn.kiwipeach.demo.service.EmployService;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author liuburu
 * @create 2018/01/08
 **/
public class EmployServiceImplTest extends SpringJunitBase {


    private ApplicationContext applicationContext;

    private EmployService employService;

    @Before
    public void sysInif() {
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        employService = applicationContext.getBean(EmployService.class);
    }


    @Test
    public void queryEmploy() {
        Employ employ = employService.queryEmploy(new BigDecimal("7369"));
        System.out.println(JSON.toJSONString(employ));
    }

    @Test
    public void queryEmployInfo() {
        Pageable pageable = new Pageable(1, 2);
        List<Employ> pageResultResponse = employService.queryEmployInfo("SALESMAN", pageable);
    }


    /**
     * 测试多数据源事务
     */
    @Test
    public void testMulityDTransactionService() {
        Employ mysqlEmploy = new Employ(new BigDecimal(4455), "悟空-mysql", "弼马温", new Date());
        Employ oracleEmploy = new Employ(new BigDecimal(5566), "悟空-oracle", "弼马温", new Date());
        EmployService employService = applicationContext.getBean(EmployService.class);
        int i = employService.testMulityDTransactionService(mysqlEmploy, oracleEmploy, false);
        Assert.assertEquals(2, i);
    }


}