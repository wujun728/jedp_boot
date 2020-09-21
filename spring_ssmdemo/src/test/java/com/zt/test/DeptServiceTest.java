package com.zt.test;

import com.zt.pojo.SysDept;
import com.zt.service.IDeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by CDHong on 2018/4/3.
 */
@ContextConfiguration("classpath:spring/spring-mybatis.xml")  //加载spring-mybatis.xml配置文件
@RunWith(SpringJUnit4ClassRunner.class) //使用spring和junit4的整合类启动
public class DeptServiceTest {

    //注入IDeptSerivce的实例，注意我们这里是使用了接口代理，所有需要使用接口来接受
    @Autowired private IDeptService deptService;

    @Test //建立Junit的测试类，注意这里该方法的访问修饰符和返回值一定是public void
    public void testFindDeptById(){
        SysDept dept = deptService.findDeptById(2);
        System.out.println(dept.getDeptName());
    }
}
