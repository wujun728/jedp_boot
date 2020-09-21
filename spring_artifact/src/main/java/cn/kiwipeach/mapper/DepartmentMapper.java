package cn.kiwipeach.mapper;

import cn.kiwipeach.modal.Department;

import java.math.BigDecimal;

/**
 * Create Date: 2017/10/31
 * Description: 部门操作接口
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public interface DepartmentMapper {
    int deleteByPrimaryKey(BigDecimal deptno);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(BigDecimal deptno);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}