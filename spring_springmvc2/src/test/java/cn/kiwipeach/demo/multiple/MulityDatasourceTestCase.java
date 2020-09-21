/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.multiple;

import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.mapper.*;
import cn.kiwipeach.demo.framework.response.Pageable;
import cn.kiwipeach.demo.service.EmployService;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Create Date: 2018/01/14
 * Description: 测试多数据源,特别提醒：Mapper不要从SpringJunit中获取，否则多数据源自动切换功能不生效，将会使用默认数据源进行操作
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class MulityDatasourceTestCase {

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    /**
     * 测试多数据源简单的操作：分页从mysql、oracle数据源的中获取相同编号的员工信息
     */
    @Test
    public void testMulityDS1() {
        OracleEmployMapper oracleEmployMapper = applicationContext.getBean(OracleEmployMapper.class);
        MysqlEmployMapper mysqlEmployMapper = applicationContext.getBean(MysqlEmployMapper.class);
//        CustomMultipleDataSource.setDataSourceKey("mysqlDataSource"); 手动切换方式
        Employ mysqlEmploy = mysqlEmployMapper.selectByPrimaryKey(new BigDecimal("7777"));
        System.out.println(JSON.toJSONString(mysqlEmploy));
//        CustomMultipleDataSource.setDataSourceKey("oracleDataSource");//手动切换方式
        Employ oracleEmploy = oracleEmployMapper.selectByPrimaryKey(new BigDecimal("7777"));
        System.out.println(JSON.toJSONString(oracleEmploy));

    }

    /**
     * 测试在同一个Mapper文件中@Datasource位于类级别与位于方法级别的特性：
     * 当方法级别没有@Datasource注解时会默认选择类上面注解的数据源，如果类
     * 上面也没有@Datasource注解，那么将采用全局配置的默认数据源，在spring-mybatis.xml
     * 的CustomMultipleDataSource Bean中的属性defaultTargetDataSource
     */
    @Test
    public void testMultiyDS2() {
        MethodMulityEmployMapper methodMulityEmployMapper = applicationContext.getBean(MethodMulityEmployMapper.class);
        Employ methodMulityEmploy1 = methodMulityEmployMapper.mysqlSelectByPrimaryKey(new BigDecimal("7777"));
        System.out.println(JSON.toJSONString(methodMulityEmploy1));
        Employ methodMulityEmploy2 = methodMulityEmployMapper.oracleSelectByPrimaryKey(new BigDecimal("7777"));
        System.out.println(JSON.toJSONString(methodMulityEmploy2));
    }


    /**
     * 测试多数据源下的分页查询操作，分页查询oracle、mysql不同数据源pageNO=2,pageSize=2
     * 的分页数据。
     */
    @Test
    public void testMulityDSPageQuery() {
        EmployMapper employMapper = applicationContext.getBean(EmployMapper.class);
        Pageable pageable1 = new Pageable(2, 2);
        Pageable pageable2 = new Pageable(2, 2);
        List<String> stringList = Arrays.asList("SALESMAN", "MANAGER");
        //oracle数据源查询
        System.out.println("Oracle分页前：" + JSON.toJSONString(pageable1));
        employMapper.selectByJob5(stringList, pageable1);
        System.out.println("Oracle分页后：" + JSON.toJSONString(pageable1));
        //mysql数据源查询
        System.out.println("MySQL分页前：" + JSON.toJSONString(pageable2));
        employMapper.selectByJob6(stringList, pageable2);
        System.out.println("MySQL分页后：" + JSON.toJSONString(pageable2));
    }


    /**
     * 测试在服务层的分页功能是否正常
     */
    @Test
    public void testMulityDSPageQueryService() {
        EmployService employService = applicationContext.getBean(EmployService.class);
        Pageable<Employ> pageable = new Pageable<Employ>(2, 2);
        List<String> stringList = Arrays.asList("SALESMAN", "MANAGER");
        Pageable resultPage = employService.pqgeQueryEmployInfo(stringList, pageable, "oracle");
        System.out.println(resultPage.getTotalNum());
    }


    @Test
    public void testMulityDTransaction() {
        Employ mysqlEmploy = new Employ(new BigDecimal(45678), "孙悟空", "弼马温", new Date());
        Employ oracleEmploy = new Employ(new BigDecimal(45678), "孙悟空", "弼马温", new Date());
        OracleEmployMapper oracleEmployMapper = applicationContext.getBean(OracleEmployMapper.class);
        int oracleInsertRow = oracleEmployMapper.insertSelective(oracleEmploy);
        System.out.println("oracle插入结果:" + oracleInsertRow);
        MysqlEmployMapper mysqlEmployMapper = applicationContext.getBean(MysqlEmployMapper.class);
        int mysqlInsertRow = mysqlEmployMapper.insertSelective(mysqlEmploy);
        System.out.println("mysql插入结果:" + mysqlInsertRow);
        System.out.println(mysqlInsertRow + oracleInsertRow);
    }



}
