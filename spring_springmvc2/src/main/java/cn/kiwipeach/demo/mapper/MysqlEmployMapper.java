package cn.kiwipeach.demo.mapper;


import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.annotations.Datasource;

import java.math.BigDecimal;

/**
 * Create Date: 2017/10/31
 * Description: 员工操作接口
 *
 * @author kiwipeach [1099501218@qq.com]
 */
@Datasource("mysqlDataSource")
public interface MysqlEmployMapper {

    Employ selectByPrimaryKey(BigDecimal empno);

    int insertSelective(Employ record);

}