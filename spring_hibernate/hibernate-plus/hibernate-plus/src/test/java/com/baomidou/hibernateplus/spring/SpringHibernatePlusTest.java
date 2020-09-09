package com.baomidou.hibernateplus.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import com.baomidou.hibernateplus.HibernateSpringSessionFactoryBean;
import com.baomidou.hibernateplus.condition.SelectWrapper;
import com.baomidou.hibernateplus.entity.page.Page;
import com.baomidou.hibernateplus.spring.dao.DemoDao;
import com.baomidou.hibernateplus.spring.dao.DemoDaoImpl;
import com.baomidou.hibernateplus.spring.po.Tdemo;

/**
 * <p>
 * Hibernate Plus 单元测试
 * </p>
 *
 * @author Caratacus
 * @date 2016-12-2
 */
public class SpringHibernatePlusTest {

    static {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/hibernate-plus?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("521");
        dataSource.setMaxTotal(1000);

        HibernateSpringSessionFactoryBean sessionFactoryBean = new HibernateSpringSessionFactoryBean();
        sessionFactoryBean.setType("master");
        sessionFactoryBean.setDataSource(dataSource);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.put("hibernate.show_sql", false);
        hibernateProperties.put("hibernate.format_sql", false);
        hibernateProperties.put("hibernate.use_sql_comments", true);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.setPackagesToScan("com.baomidou.hibernateplus.spring.po");
        try {
            sessionFactoryBean.afterPropertiesSet();
        } catch (Exception e) {
            System.out.println("Hibernate-Plus出错啦!");
        }
    }

    private final DemoDao demoDao = new DemoDaoImpl();

    @Test
    public void test1() {
        List<Tdemo> lists = new ArrayList<Tdemo>();
        for (int i = 0; i <= 100; i++) {
            Tdemo tdemo = new Tdemo();
            tdemo.setDemo1(i + "");
            tdemo.setDemo2(i + "");
            tdemo.setDemo3(i + "");
            lists.add(tdemo);
        }
        // 批量插入
        boolean insertBatch = demoDao.insertBatch(lists, 30);
        System.out.println(insertBatch);
        // Condition 链式查询列表
        List<Tdemo> tdemoList = demoDao.selectList(SelectWrapper.instance().le("id", 10));
        System.out.println(tdemoList);
        Map map = new HashMap();
        map.put("id", 99L);
        // 根据Condition 查询单条记录
        List<Tdemo> tdemo = demoDao.selectList(SelectWrapper.instance().eq("id", 10));
        System.out.println(tdemo);
        List<Map<String, Object>> mapList = demoDao.selectMaps(SelectWrapper.instance().ge("id", 80));
        System.out.println(mapList);

        int selectCount2 = demoDao.selectCount(SelectWrapper.instance().ge("id", 80));
        System.out.println(selectCount2);
        Page page = new Page(1, 20);
        page.setOrderByField("id");
        page.setAsc(false);
        // Condition链式查询分页返回Map
        Page selectMapPage = demoDao.selectMapsPage(SelectWrapper.instance().ge("id", 50), page);
        System.out.println(selectMapPage);
        // Condition链式查询分页返回VO
        Page selectPage2 = demoDao.selectPage(SelectWrapper.instance().ge("id", 50), page);
        System.out.println(selectPage2);
        // Condition链式 删除单条记录
        demoDao.delete(SelectWrapper.instance().eq("id", 1));
        List<Tdemo> tdemos = demoDao.selectList(SelectWrapper.instance());
        Iterator<Tdemo> iterator = tdemos.iterator();
        while (iterator.hasNext()) {
            Tdemo tdemo3 = iterator.next();
            tdemo3.setDemo1(tdemo3.getDemo1() + "Caratacus Plus 1");
            tdemo3.setDemo2(tdemo3.getDemo2() + "Caratacus Plus 2");
            tdemo3.setDemo3(tdemo3.getDemo3() + "Caratacus Plus 3");
        }
        // 批量修改
        demoDao.updateBatch(tdemos, 30);
        // Condition链式 删除所有记录
        demoDao.delete(SelectWrapper.DEFAULT);
    }
}
