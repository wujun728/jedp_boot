package cn.kiwipeach.mapper;

import cn.kiwipeach.modal.Employ;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * ${DESCRIPTION}
 *
 * @author liuburu
 * @create 2017/10/31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-mybatis.xml"
})
public class EmployMapperTest {

    @Autowired
    private EmployMapper employMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    public void selectByPrimaryKey() throws Exception {
        //1.数据源测试
//        System.out.println(dataSource.getConnection());
        //2.测试Mapper查询
        BigDecimal empno = new BigDecimal(7777);
        Employ employ = employMapper.selectByPrimaryKey(empno);
        System.out.println(JSON.toJSONString(employ));
    }

}