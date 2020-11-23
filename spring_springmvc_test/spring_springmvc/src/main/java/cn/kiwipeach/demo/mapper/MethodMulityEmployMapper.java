package cn.kiwipeach.demo.mapper;


import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.annotations.Datasource;

import java.math.BigDecimal;

/**
 * Create Date: 2017/10/31
 * Description: 员工操作接口
 *
 * @author Wujun
 */
@Datasource("oracleDataSource")
public interface MethodMulityEmployMapper {

    @Datasource("mysqlDataSource")
    Employ mysqlSelectByPrimaryKey(BigDecimal empno);


    Employ oracleSelectByPrimaryKey(BigDecimal empno);

}