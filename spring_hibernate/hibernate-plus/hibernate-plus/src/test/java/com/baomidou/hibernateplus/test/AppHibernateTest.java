package com.baomidou.hibernateplus.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.baomidou.hibernateplus.model.AppTestModel;
import com.baomidou.hibernateplus.utils.HibernateUtils;

/**
 * <p>
 * AppHibernateTest
 * </p>
 *
 * @author Caratacus
 * @date 2016-10-14
 */
public class AppHibernateTest {

    /**
     * 测试AppHibernateTest
     */
    @Test
    public void test1() throws Exception {
        String hql = HibernateUtils.getListHql(AppTestModel.class);
        System.out.println(hql);
        Assert.assertEquals(" FROM AppTestModel", hql);
    }

    @Test
    public void test2() throws Exception {
        String order = "c1,c2";
        String[] arrays = new String[]{"c1", "c2"};
        List list = new ArrayList();
        list.add("111");
        list.add("222");
        list.add("333");
        Map map = new HashMap();
        map.put("key1", "key1");
        map.put("key2", "key2");
        map.put("key3", new String[]{"c1", "c2"});
        map.put("key4", list);
        String hql = HibernateUtils.getCountHql(AppTestModel.class, map);
        System.out.println(hql);
        Assert.assertEquals(
                "SELECT COUNT(0) FROM AppTestModel WHERE key1 = :key1 AND key2 = :key2 AND key3 IN :key3 AND key4 IN :key4", hql);
    }
}
