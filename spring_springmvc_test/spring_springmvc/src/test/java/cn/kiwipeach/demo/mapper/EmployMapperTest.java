package cn.kiwipeach.demo.mapper;

import cn.kiwipeach.demo.SpringJunitBase;
import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.domain.dto.EmployDeptDTO;
import cn.kiwipeach.demo.framework.response.Pageable;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author liuburu
 * @create 2018/01/08
 **/
public class EmployMapperTest extends SpringJunitBase {

    @Qualifier("oracleDataSource")
    @Autowired
    private DataSource dataSource;

    @Test
    public void testDruidDatesource() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "select * from emp where empno = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "7777");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }
        connection.close();
    }


    @Autowired
    private EmployMapper employMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
        Employ employ = employMapper.selectByPrimaryKey(new BigDecimal(7369));
        System.out.println(JSON.toJSONString(employ));
    }

    @Test
    public void insertSelective() throws Exception {
        Employ employ = employMapper.selectByPrimaryKey(new BigDecimal(7369));
        employ.setEmpno(new BigDecimal("6666"));
        employ.setEname("测试用户");
        int insertRow = employMapper.insertSelective(employ);
        System.out.println("插入影响记录行数:" + insertRow);
    }

    @Test
    public void selectByJob1() throws Exception {
        Pageable pageable = new Pageable(2, 2);
        System.out.println("分页前：" + JSON.toJSONString(pageable));
        employMapper.selectByJob1("SALESMAN", pageable);
        System.out.println("分页后：" + JSON.toJSONString(pageable));
    }

    @Test
    public void selectByJob2() throws Exception {
        Employ employ = new Employ();
        employ.setJob("SALESMAN");
        Pageable pageable = new Pageable(2, 2);
        System.out.println("分页前：" + JSON.toJSONString(pageable));
        employMapper.selectByJob2(employ, pageable);
        System.out.println("分页后：" + JSON.toJSONString(pageable));
    }

    @Test
    public void selectByJob3() throws Exception {
        Pageable pageable = new Pageable(2, 2);
        List<String> stringList = Arrays.asList("SALESMAN", "MANAGER");
        System.out.println("分页前：" + JSON.toJSONString(pageable));
        employMapper.selectByJob3(stringList, pageable);
        System.out.println("分页后：" + JSON.toJSONString(pageable));
    }

    @Test
    public void selectByJob4() throws Exception {
        Pageable pageable = new Pageable(1, 3);
        List<String> stringList = Arrays.asList("SALESMAN", "MANAGER");
        String deptno = "30";
        System.out.println("分页前：" + JSON.toJSONString(pageable));
        employMapper.selectByJob4(stringList, deptno, pageable);
        System.out.println("分页后：" + JSON.toJSONString(pageable));
    }

    @Test
    public void selectEmpDeptInfo() throws Exception {
        EmployDeptDTO employDeptDTO = employMapper.selectEmpDeptInfo("7777");
        System.out.println(JSON.toJSONString(employDeptDTO));
    }


}