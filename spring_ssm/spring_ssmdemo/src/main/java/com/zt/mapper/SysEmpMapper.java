package com.zt.mapper;

import com.zt.pojo.SysEmp;

public interface SysEmpMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(SysEmp record);

    int insertSelective(SysEmp record);

    SysEmp selectByPrimaryKey(Integer empId);

    int updateByPrimaryKeySelective(SysEmp record);

    int updateByPrimaryKey(SysEmp record);
}