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
package cn.kiwipeach.demo.service.impl;

import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.domain.dto.EmployDeptDTO;
import cn.kiwipeach.demo.enums.business.BusinessEnums;
import cn.kiwipeach.demo.framework.annotations.Datasource;
import cn.kiwipeach.demo.framework.datasource.CustomMultipleDataSource;
import cn.kiwipeach.demo.framework.exception.BusinessException;
import cn.kiwipeach.demo.framework.response.Pageable;
import cn.kiwipeach.demo.mapper.EmployMapper;
import cn.kiwipeach.demo.mapper.MysqlEmployMapper;
import cn.kiwipeach.demo.mapper.OracleEmployMapper;
import cn.kiwipeach.demo.service.EmployService;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Create Date: 2018/01/08
 * Description: 员工服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 */
@Service
public class EmployServiceImpl implements EmployService {

    private static final int DEFALUT_AFFECT_ROW = -1;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private EmployMapper employMapper;

    //@Autowired
    private OracleEmployMapper oracleEmployMapper;

    //@Autowired
    private MysqlEmployMapper mysqlEmployMapper;

    @Override
    public Employ queryEmploy(BigDecimal empno) {
        //三种常见异常：1.不传异常code 2.传异常code和message 3.传枚举入参
        if ("7777".equals(String.valueOf(empno))) {
            throw new BusinessException("员工编号有毒[只传message]", empno);
        } else if ("6666".equals(String.valueOf(empno))) {
            throw new BusinessException("BUS_6666", "员工编号有毒[传异常code和message]", empno);
        } else if ("5555".equals(String.valueOf(empno))) {
            throw new BusinessException(BusinessEnums.EMP_BUS_TEST, empno);
        } else if ("4444".equals(String.valueOf(empno))) {
            throw new NullPointerException("神奇的空指针异常");
        }
        return employMapper.selectByPrimaryKey(empno);
    }

    @Override
    public List<Employ> queryAll() {
        return employMapper.selectAll();
    }

    @Override
    public EmployDeptDTO queryEmployDeptMsg(String empno) {
        return employMapper.selectEmpDeptInfo(empno);
    }

    @Override
    public int saveOrUpdate(Employ employ) {
        BigDecimal empno = employ.getEmpno();
        int affectRow = DEFALUT_AFFECT_ROW;
        if (employ == null) {
            throw new BusinessException(BusinessEnums.EMP_EMPTY);
        }
        if (empno == null) {
            //生成主键插入数据库
            String uuid = String.valueOf(Math.random() * 1000);
            employ.setEmpno(new BigDecimal(uuid));
            affectRow = employMapper.insertSelective(employ);
        } else {
            affectRow = employMapper.updateByPrimaryKeySelective(employ);
        }
        return affectRow;
    }

    @Override
    public int remove(String empno) {
        if (empno == null) {
            throw new BusinessException(BusinessEnums.EMPNO_EMPTY);
        } else {
            return employMapper.deleteByPrimaryKey(new BigDecimal(empno));
        }
    }

    @Override
    public List<Employ> queryEmployInfo(String job, Pageable pageable) {
        return employMapper.selectByJob1(job, pageable);
    }

    @Override
    public Pageable<Employ> pqgeQueryEmployInfo(List<String> jobs, Pageable<Employ> pageable, String dbtype) {
        List<String> stringList = Arrays.asList("SALESMAN", "MANAGER");
        //oracle数据源查询
        if ("oracle".equals(dbtype)) {
            employMapper.selectByJob5(stringList, pageable);
        } else if ("mysql".equals(dbtype)) {
            //mysql数据源查询
            employMapper.selectByJob6(stringList, pageable);
        } else {
            throw new BusinessException("不支持该数据库类型分页查询操作:" + dbtype);
        }
        return pageable;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource mysqlDataSource;

    /**
     * 这里不支持跨库数据库的事务，即@Transactional 注解无效，
     * 只能支持对一个数据库的事务，如果需要一定要写跨库事务，那么需要手动编写业务逻辑代码。
     * 简而言之：master数据库事务能够回滚，而slave的事务需要手动使用jdbc回滚
     */
    @Override
    @Transactional
    public int testMulityDTransactionService(Employ mysqlEmploy, Employ oracleEmploy, boolean isRollback) {
        int oracleInsertRow = oracleEmployMapper.insertSelective(oracleEmploy);
        System.out.println("oracle插入结果:" + oracleInsertRow);
        Connection connection = null;
        try {
            connection = mysqlDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int mysqlInsertRow = 0;
        try {
            mysqlInsertRow = mysqlInsert(mysqlEmploy, connection);
            System.out.println("mysql插入结果:" + mysqlInsertRow);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (isRollback) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new BusinessException("插入员工失败，事务回滚");
        }
        return mysqlInsertRow + oracleInsertRow;
    }


    private int mysqlInsert(Employ employ, Connection connection) throws SQLException {
        String sql = "insert into emp(empno,ename,job) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(employ.getEmpno()));
        preparedStatement.setString(2, employ.getEname());
        preparedStatement.setString(3, employ.getJob());
        int i = preparedStatement.executeUpdate();
        return i;
    }


}
