package com.wuzy.sys.service.impl;

import com.wuzy.sys.dao.SysRoleMapper;
import com.wuzy.sys.model.SysRole;
import com.wuzy.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by wuzy
 * on 2017-01-01 22:01.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;


    /**
     * 通过userId查询该用户拥有的角色set
     *
     * @param userId
     * @return
     */
    public Set<String> getRoleListByUid(Long userId) {
        return sysRoleMapper.getRoleListByUid(userId);
    }
}
