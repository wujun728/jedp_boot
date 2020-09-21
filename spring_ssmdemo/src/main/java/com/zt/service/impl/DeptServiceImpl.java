package com.zt.service.impl;

import com.zt.mapper.SysDeptMapper;
import com.zt.pojo.SysDept;
import com.zt.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {

    //注入需要的Mapper实例，用于方法调用
    @Autowired private SysDeptMapper sysDeptMapper;

    /**
     * 功能:根据ID查询部门信息
     * @param deptNo 部门编号
     * @return 返回部门信息
     */
    @Override
    public SysDept findDeptById(Integer deptNo) {
        return sysDeptMapper.selectByPrimaryKey(deptNo);
    }
}
