package com.github.zhangkaitao.dbtest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * User: zhangkaitao
 * Date: 14-8-4
 * Time: ����8:57
 * Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:spring-datasource.xml")
@Sql(value = {"classpath:schema.sql", "classpath:init-data.sql", "classpath:updated-data.sql"},
        config =
        @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--",
                dataSource = "dataSource1", transactionManager = "txManager1"))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClassLevelSqlTest {

    private JdbcTemplate jdbcTemplate1;
    @Autowired
    @Qualifier("dataSource1")
    public void setDataSource1(DataSource dataSource1) {
        this.jdbcTemplate1 = new JdbcTemplate(dataSource1);
    }

    @Test
    public void test01_simple() {
        Assert.assertEquals(
                Integer.valueOf(3),
                jdbcTemplate1.queryForObject("select count(1) from users", Integer.class));
    }


}