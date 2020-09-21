package com.github.zhangkaitao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: zhangkaitao
 * Date: 14-7-31
 * Time: ����8:00
 * Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-direct-field.xml")
public class DirectFieldTest {
    @Autowired
    private Bean bean;
    @Test
    public void testDirectFieldAccess() { //DirectFieldBindingResult
        //Ƕ������/���ʶ����ֶ�����
        DirectFieldAccessor accessor = new DirectFieldAccessor(bean);
        //���Ƕ�׶���Ϊnull���ֶδ���
        accessor.setAutoGrowNestedPaths(true);
        //�����ֶ�ֵ
        accessor.setPropertyValue("bean2.name", "zhangsan");
        //��ȡ�ֶ�ֵ
        System.out.println(accessor.getPropertyValue("bean2.name"));
    }
}
